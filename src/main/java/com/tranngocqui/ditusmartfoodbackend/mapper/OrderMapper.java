package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderValidationResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderItemStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order from() {
        return Order.builder().build();
    }

    public OrderClientCreateResponse to(Order order, List<OrderClientCreateResponse.UnavailableItem> unavailableItems) {
        return OrderClientCreateResponse.builder()
                .orderId(order.getOrderId())
                .items(to(order.getOrderItems()))
                .recipientName(order.getRecipientName())
                .recipientPhone(order.getRecipientPhone())
                .NOTE(order.getNote())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod().name())
                .deliveryMethod(order.getDeliveryMethod().name())
                .unavailableItems(unavailableItems)
                .createdAt(order.getCreatedAt())
                .customerAddress(order.getCustomerAddress())
                .build();
    }


    public List<OrderClientCreateResponse.OrderItem> to(List<OrderItem> list) {
        return list.stream().map(this::to).toList();
    }

    public OrderClientCreateResponse.OrderItem to(OrderItem item) {
        return OrderClientCreateResponse.OrderItem.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .priceAtOrderedTime(item.getPriceAtOrderedTime())
                .build();
    }

    public OrderValidationResponse to(Order order) {
        Map<Boolean, List<OrderItem>> partitioned = order.getOrderItems().stream()
                .collect(Collectors.partitioningBy(
                        oi -> oi.getStatus() == OrderItemStatus.VALID
                ));

        List<OrderValidationResponse.Item> validItems = mapItems(
                partitioned.get(true), true
        );
        List<OrderValidationResponse.Item> invalidItems = mapItems(
                partitioned.get(false), false
        );

        return OrderValidationResponse.builder()
                .orderId(order.getOrderId())
                .validItems(validItems)
                .invalidItems(invalidItems)
                .accepted(validItems.size())
                .rejected(invalidItems.size())
                .numberOfItems(validItems.size() + invalidItems.size())
                .build();
    }

    private List<OrderValidationResponse.Item> mapItems(
            List<OrderItem> items,
            boolean isValid) {

        return items.stream()
                .map(oi -> OrderValidationResponse.Item.builder()
                        .productId(oi.getProductId())
                        .productName(oi.getProductName())
                        .quantity(oi.getQuantity())
                        .stock(oi.getStock())
                        .accepted(isValid)
                        .reason(oi.getReason().name())
                        .build())
                .toList();
    }
}
