package com.tranngocqui.ditusmartfoodbackend.service.application.order;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.order.OrderAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CreateOrderResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.*;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderItemMapper;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderMapper;
import com.tranngocqui.ditusmartfoodbackend.service.application.item.ItemService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.deliverymethod.DeliveryMethodDomainService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.item.ItemDomainService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.order.OrderDomainService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.orderitem.OrderItemDomainService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.paymentmethod.PaymentMethodDomainService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.user.UserDomainService;
import com.tranngocqui.ditusmartfoodbackend.ultis.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final UserDomainService userDomainService;
    private final PaymentMethodDomainService paymentMethodDomainService;
    private final DeliveryMethodDomainService deliveryMethodDomainService;
    private final ItemService itemService;
    private final ItemDomainService itemDomainService;
    private final OrderItemDomainService orderItemDomainService;
    private final OrderItemMapper orderItemMapper;
    private final OrderDomainService orderDomainService;


    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
//        DeliveryMethod deliveryMethod = deliveryMethodService.getById(request.getPaymentMethodId());
//
//        Order order = orderMapper.toOrder(request);
//
//        order.setDeliveryMethod(deliveryMethod);
//
//        User user = userService.getById(request.getUserId());
//
//        order.setUser(user);
//
//        List<OrderItem> items = request.getItems().stream()
//                .map(itemRequest -> {
//                    Item item = itemRepository.findById(UUID.fromString(itemRequest.getItemId())).orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND));
//
//                    OrderItem orderItem = new OrderItem();
//
//                    orderItem.setOrder(order);
//
//                    orderItem.setItem(item);
//
//                    orderItem.setQuantity(itemRequest.getQuantity());
//
//                    orderItem.setPriceAtOrderTime(item.getPrice());
//
//                    return orderItem;
//                })
//                .toList();
//
//        order.setItems(items);
//
//        order.setDeliveryMethod(deliveryMethodRepository.findById(UUID.fromString(request.getDeliveryMethodId())).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND)));
//
//        order.setShippingFee(order.getDeliveryMethod().getPrice());
//
//        BigDecimal totalAmount = request.getItems().stream()
//                .map(itemRequest -> {
//                    Item item = itemRepository.findById(UUID.fromString(itemRequest.getItemId())).orElseThrow(() -> new AppException(ErrorCode.MENU_ITEM_NOT_FOUND));
//
//                    BigDecimal price = item.getPrice();
//                    int quantity = itemRequest.getQuantity();
//
//                    return price.multiply(BigDecimal.valueOf(quantity));
//                })
//                .reduce(BigDecimal.ZERO, BigDecimal::add)
//                .add(order.getShippingFee() == null ? BigDecimal.ZERO : order.getShippingFee());
//
//        order.setTotalAmount(totalAmount);
//
//        order.setPaymentMethod(paymentMethodRepository.findById(UUID.fromString(request.getPaymentMethodId())).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED)));
//
//        return orderMapper.toCreateOrderResponse(orderRepository.save(order));
        return null;
    }

    @Override
    public OrderAdminCreateResponse createOrder(OrderAdminCreateRequest request) {

        User user = userDomainService.getByIdOrThrow(request.userId());
        PaymentMethod paymentMethod = paymentMethodDomainService.getByIdOrThrow(request.paymentMethodId());
        DeliveryMethod deliveryMethod = deliveryMethodDomainService.getByIdOrThrow(request.deliveryMethodId());
        List<OrderItem> items = ParameterValidator.requireNonEmptyList(listValidItemAndQuantity(request.orderItems()), ErrorCode.EMPTY_LIST_ITEM);

        Order order = buildOrder(user, paymentMethod, deliveryMethod, items);

        return OrderAdminCreateResponse.builder()
                .orderId(String.valueOf(order.getId()))
                .deliveryMethodName(order.getDeliveryMethod().getFullName())
                .paymentMethodName(order.getPaymentMethod().getFullName())
                .build();
    }

    @Override
    public Page<OrderAdminResponse> getOrders(Pageable pageable) {
        return null;
//        return findAll(pageable).map(orderMapper::toOrderAdminResponse);
    }

    @Override
    public OrderAdminResponse getOrderById(String id) {
        return null;
//        return orderMapper.toOrderAdminResponse(findById(UUID.fromString(id)));
    }

    @Override
    public void deleteOrderById(String id) {
//        deleteById(UUID.fromString(id));
    }

    @Override
    public OrderAdminResponse updateOrder(String id, OrderAdminUpdateRequest request) {
        return null;
    }


    @Override
    public Order order(CreateOrderRequest request) {
        return null;
//        Order order = orderMapper.toOrder(request);
//
//        order.setUser(userService.getById(request.getUserId()));
//
//        order.setDeliveryMethod(deliveryMethodRepository.findById(UUID.fromString(request.getDeliveryMethodId())).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND)));
//
//        order.setShippingFee(order.getDeliveryMethod().getPrice());
//
//        return orderRepository.save(order);
    }

    @Override
    public void processAfterPayment(Order order) {

    }

    private BigDecimal calculateItemsAmount(List<OrderItem> items) {
        return items.stream().map(item -> {
            return item.getPriceAtOrderedTime().multiply(BigDecimal.valueOf(item.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order buildOrder(User user, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, List<OrderItem> items) {

        BigDecimal itemsAmount = calculateItemsAmount(items);

        Order order = Order.builder()
                .user(user)
                .paymentMethod(paymentMethod)
                .deliveryMethod(deliveryMethod)
                .orderItems(items)
                .totalAmount(itemsAmount)
                .recipientName(user.getFullName())
                .recipientPhone(user.getPhone())
                .shippingFee(deliveryMethod.getPricePerKm())
                .paymentMethodName(paymentMethod.getFullName())
                .build();

        items.forEach(i -> i.setOrder(order));

        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                .order(order)
                .amount(itemsAmount)
                .build();

        order.setTransaction(paymentTransaction);

        return orderDomainService.createOrder(order);

    }

    private List<OrderItem> listValidItemAndQuantity(List<OrderItemCreateRequest> requests) {
        return Optional.ofNullable(requests)
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(orderItem -> {
                    Item item = itemDomainService.getByIdOrNull(String.valueOf(orderItem.itemId()));
                    if (item == null) return Stream.empty();
                    int validQuantity = Math.min(orderItem.quantity(), item.getStock());
                    return Stream.of(OrderItem.builder()
                            .item(item)
                            .priceAtOrderedTime(item.getPrice())
                            .quantity(validQuantity)
                            .build());
                })
                .collect(Collectors.toList());
    }

}
