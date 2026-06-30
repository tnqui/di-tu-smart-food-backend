package com.tranngocqui.ditusmartfoodbackend.service.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.*;
import com.tranngocqui.ditusmartfoodbackend.dto.internal.Location;
import com.tranngocqui.ditusmartfoodbackend.entity.*;
import com.tranngocqui.ditusmartfoodbackend.enums.*;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.service.address.AddressService;
import com.tranngocqui.ditusmartfoodbackend.service.fee.FeeService;
import com.tranngocqui.ditusmartfoodbackend.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final FeeService feeService;
    private final AddressService addressService;

    @Override
    @Transactional
    public OrderClientCreateResponse create(OrderClientCreateRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @Override
    public OrderValidationResponse validate(OrderValidationRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<UUID, Integer> mapIdQuantityRequest = new HashMap<>();

        for (OrderValidationRequest.OrderItemRequest requestItem : request.requestItems()) {
            if (mapIdQuantityRequest.containsKey(requestItem.productId())) {
                throw new AppException(ErrorCode.DUPLICATE_PRODUCT);
            }
            mapIdQuantityRequest.put(requestItem.productId(), requestItem.quantity());
        }

        List<Product> products = productService.findByIds(mapIdQuantityRequest.keySet().stream().toList());

        if (products.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_LIST_PRODUCT);
        }

        Map<UUID, Product> mapIdProduct = products.stream().collect(Collectors.toMap(BaseEntity::getId, p -> p));

        Map<Boolean, List<OrderValidationRequest.OrderItemRequest>> mapItems = request.requestItems().stream().collect(Collectors.groupingBy(oi -> {
            Product p = mapIdProduct.get(oi.productId());
            return p != null && p.getStock() >= oi.quantity();
        }));

        Order order = new Order();

        List<OrderItem> validItems = mapItems.get(true).stream().map(oi ->
        {
            Product p = mapIdProduct.get(oi.productId());
            return OrderItem.builder()
                    .quantity(oi.quantity())
                    .productId(p.getId())
                    .productName(p.getName())
                    .stock(p.getStock())
                    .priceAtOrderedTime(p.getPrice())
                    .order(order)
                    .status(OrderItemStatus.VALID)
                    .reason(OrderItemReason.OK)
                    .build();
        }).collect(Collectors.toList());

        List<OrderItem> invalidItems = mapItems.get(false).stream().map(oi ->
        {
            Product p = mapIdProduct.get(oi.productId());
            OrderItemReason reason = null;

            if (p == null) {
                return OrderItem.builder()
                        .quantity(oi.quantity())
                        .productId(oi.productId())
                        .productName(null)
                        .priceAtOrderedTime(null)
                        .stock(null)
                        .order(order)
                        .status(OrderItemStatus.INVALID)
                        .reason(OrderItemReason.PRODUCT_NOT_FOUND)
                        .build();
            } else {
                if (p.getStock() == 0) {
                    reason = OrderItemReason.OUT_OF_STOCK;
                } else if (p.getStock() < oi.quantity()) {
                    reason = OrderItemReason.INSUFFICIENT_STOCK;
                }
                return OrderItem.builder()
                        .quantity(oi.quantity())
                        .productId(oi.productId())
                        .productName(p.getName())
                        .priceAtOrderedTime(p.getPrice())
                        .stock(p.getStock())
                        .order(order)
                        .status(OrderItemStatus.INVALID)
                        .reason(reason)
                        .build();
            }
        }).toList();

        for (OrderItem validItem : validItems) {
            Product p = mapIdProduct.get(validItem.getProductId());
            p.setStock(p.getStock() - validItem.getQuantity());
        }

        order.setLineItemTotal(validItems.stream().map(i -> i.getPriceAtOrderedTime().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));

        validItems.addAll(invalidItems);
        order.setOrderItems(validItems);
        order.setUser(user);
        productService.saveAll(products);
        Order savedOrder = save(order);
        return orderMapper.to(savedOrder);
    }

    @Override
    @Transactional
    public void customerConfirmOrder(String orderId, OrderConfirmRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = findByOrderId(orderId);

        if (!order.getUser().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.ORDER_FORBIDDEN);
        }

        if (order.getStatus() != OrderStatus.PENDING && order.getPaymentMethod() != PaymentMethod.COD) {
            throw new AppException(ErrorCode.ORDER_IS_PROCESSING);
        }

        Location location = addressService.getLocationAndCheckValidByRefId(request.refId());
        BigDecimal shippingFee = feeService.calculateShippingFee(order, location.distance());

        order.setLatitude(location.lat());
        order.setLongitude(location.lng());
        order.setCustomerAddress(location.display());
        order.setDistance(location.distance());
        order.setShippingFee(shippingFee);
        order.setPaymentMethod(request.paymentMethod());
        order.setDeliveryMethod(request.deliveryMethod());
        order.setNote(request.note());
        order.setRecipientName(user.getFirstName() + " " + user.getLastName());
        order.setRecipientPhone(user.getPhone());
        order.setTotalAmount(order.getLineItemTotal().add(shippingFee));

        if (request.paymentMethod() == PaymentMethod.BANK_TRANSFER) {
            order.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
        } else
            order.setStatus(OrderStatus.CUSTOMER_CONFIRMED);

    }

    @Override
    public void restaurantConfirmOrder(String orderId) {
        Order order = findByOrderId(orderId);

        if (!(order.getStatus() == OrderStatus.CUSTOMER_CONFIRMED || order.getStatus() == OrderStatus.PAID)) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.RESTAURANT_CONFIRMED);
        save(order);
    }

    @Override
    public void prepareOrder(String orderId) {
        Order order = findByOrderId(orderId);

        if (!(order.getStatus() == OrderStatus.RESTAURANT_CONFIRMED || order.getStatus() == OrderStatus.PAID)) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.PREPARING);

        save(order);

    }

    @Override
    public void orderReady(String orderId) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.PREPARING) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.READY_FOR_DELIVERY);

        save(order);
    }

    @Override
    public void orderDelivering(String orderId) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.READY_FOR_DELIVERY) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.DELIVERING);

        save(order);
    }

    @Override
    public void orderArrived(String orderId) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.DELIVERING) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.ARRIVED);

        save(order);
    }

    @Override
    public void orderDelivered(String orderId) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.ARRIVED) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS);
        }

        order.setStatus(OrderStatus.DELIVERED);

        save(order);
    }

    @Override
    public void orderDeliveryFailed(String orderId, DeliveryFailReason reason) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.DELIVERING) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS, "process: " + order.getStatus().name());
        }

        order.setStatus(OrderStatus.DELIVERY_FAILED);
        order.setFailedReason(reason.name());

        save(order);
    }

    @Override
    public void redelivering(String orderId) {
        Order order = findByOrderId(orderId);

        if (order.getStatus() != OrderStatus.DELIVERY_FAILED) {
            throw new AppException(ErrorCode.ORDER_IN_ANOTHER_PROCESS, "process: " + order.getStatus().name());
        }

        order.setStatus(OrderStatus.DELIVERING);

        save(order);
    }

    @Override
    public void refund(String orderId) {

    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = findByOrderId(orderId);

        if (!order.getStatus().canCancel()) {
            throw new AppException(ErrorCode.ORDER_IS_PROCESSING);
        }

        order.setStatus(OrderStatus.CANCELLED);

        save(order);

    }

    private Order save(Order order) {
        return orderRepository.save(order);
    }

    private Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    }

    private BigDecimal calculateTotalPrice() {
        return null;
    }


}
