package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.client.order.OrderClientCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

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


}
