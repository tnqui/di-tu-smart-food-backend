package com.tranngocqui.ditusmartfoodbackend.service.order;//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.*;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.UnavailableReason;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.OrderMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.OrderRepository;
import com.tranngocqui.ditusmartfoodbackend.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public OrderClientCreateResponse create(OrderClientCreateRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<UUID, Integer> qtyMap = request.items().stream()
                .collect(Collectors.groupingBy(
                        OrderClientCreateRequest.OrderItemRequest::productId,
                        Collectors.summingInt(OrderClientCreateRequest.OrderItemRequest::quantity)
                ));

        List<Product> products = productService.findByIdsWithLock(qtyMap.keySet().stream().toList());

        if (products.isEmpty()) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        Map<UUID, Product> mapIdProduct = products.stream().collect(Collectors.toMap(BaseEntity::getId, p -> p));

        Map<Boolean, List<OrderClientCreateRequest.OrderItemRequest>> mapAvailableAndUnavailableItems = request.items().stream()
                .collect(Collectors.partitioningBy(oi -> {
                    Product p = mapIdProduct.get(oi.productId());
                    return p != null && p.getStock() >= qtyMap.get(oi.productId());
                }));

        Order order = new Order();
        order.setUser(user);
        order.setDeliveryMethod(request.deliveryMethod());
        order.setPaymentMethod(request.paymentMethod());
        order.setRecipientName(request.recipientName());
        order.setRecipientPhone(request.recipientPhone());
        order.setNote(request.note());

        List<OrderItem> validItems = mapAvailableAndUnavailableItems.get(true).stream().map(oi -> {
            Product p = mapIdProduct.get(oi.productId());
            return OrderItem.builder()
                    .order(order)
                    .priceAtOrderedTime(p.getPrice())
                    .quantity(oi.quantity())
                    .productId(p.getId())
                    .build();
        }).collect(Collectors.toList());

        if (validItems.isEmpty()) {
            throw new AppException(ErrorCode.NO_VALID_ORDER_ITEMS);
        }

        order.setOrderItems(validItems);
        Order savedOrder = save(order);

        Set<UUID> validProductIds = mapAvailableAndUnavailableItems.get(true).stream()
                .map(OrderClientCreateRequest.OrderItemRequest::productId)
                .collect(Collectors.toSet());

        products.stream()
                .filter(p -> validProductIds.contains(p.getId()))
                .forEach(p -> p.setStock(p.getStock() - qtyMap.get(p.getId())));
        productService.saveAll(products);

        List<OrderClientCreateResponse.UnavailableItem> invalidItems = mapAvailableAndUnavailableItems.get(false).stream()
                .map(oi -> {
                    Product product = mapIdProduct.get(oi.productId());
                    UnavailableReason reason;
                    String name = null;
                    int stock = 0;

                    if (product == null) {
                        reason = UnavailableReason.PRODUCT_NOT_FOUND;
                    } else {
                        name = product.getName();
                        stock = product.getStock();
                        reason = stock == 0
                                ? UnavailableReason.OUT_OF_STOCK
                                : UnavailableReason.INSUFFICIENT_STOCK;
                    }

                    return OrderClientCreateResponse.UnavailableItem.builder()
                            .id(oi.productId())
                            .name(name)
                            .stock(stock)
                            .requestQuantity(oi.quantity())
                            .reason(reason)
                            .build();
                })
                .toList();

        return orderMapper.to(savedOrder, invalidItems);
    }

    private Order save(Order order) {
        return orderRepository.save(order);
    }


}
//List<UUID> productIds = request.items().stream().map(OrderClientCreateRequest.OrderItem::productId).toList();
//
//List<Product> products = productService.findByIds(productIds);
//
//Map<UUID, Product> mapIdProduct = products
//        .stream()
//        .collect(Collectors.toMap(BaseEntity::getId, p -> p));
//
//Order order = new Order();
//
//List<OrderItem> validItems = request.items().stream()
//        .filter(oi -> mapIdProduct.containsKey(oi.productId()) && mapIdProduct.get(oi.productId()).getStock() >= oi.quantity())
//        .map(oi -> OrderItem.builder()
//                .order(order)
//                .productId(oi.productId())
//                .quantity(oi.quantity())
//                .priceAtOrderedTime(mapIdProduct.get(oi.productId()).getPrice())
//                .build())
//        .collect(Collectors.toList());
//
//        if (validItems.isEmpty()) {
//        throw new AppException(ErrorCode.ORDER_EMPTY_LIST);
//        }
//
//BigDecimal totalPrice = validItems.stream().map(i -> i.getPriceAtOrderedTime().multiply(BigDecimal.valueOf(i.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        order.setOrderItems(validItems);
//        order.setUser(user);
//        order.setRecipientPhone(request.recipientPhone());
//        order.setRecipientName(request.recipientName());
//        order.setTotalAmount(totalPrice);
//        order.setPaymentMethod(request.paymentMethod());
//        order.setDeliveryMethod(request.deliveryMethod());
//
//        if (request.items().size() != validItems.size()) {
//        order.setNote("Có điều chỉnh số lượng sản phẩm trong order");
//        } else
//                order.setNote("");
//
//Order savedOrder = save(order);
//
//        for (OrderItem validItem : validItems) {
//Product product = mapIdProduct.get(validItem.getProductId());
//            product.setStock(product.getStock() - validItem.getQuantity());
//        }
//
//        productService.saveAll(products);
//        return orderMapper.to(savedOrder);