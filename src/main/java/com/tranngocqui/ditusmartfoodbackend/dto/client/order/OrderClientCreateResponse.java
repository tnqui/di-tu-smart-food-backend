package com.tranngocqui.ditusmartfoodbackend.dto.client.order;

import com.tranngocqui.ditusmartfoodbackend.enums.OrderItemReason;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderClientCreateResponse(
        String orderId,
        List<OrderItem> items,
        OrderStatus status,
        BigDecimal totalAmount,
        BigDecimal shippingFee,
        Instant createdAt,
        String customerAddress,
        String matchAddress,
        String recipientName,
        String recipientPhone,
        String paymentMethod,
        String deliveryMethod,
        String NOTE,
        List<UnavailableItem> unavailableItems
) {
    @Builder
    public record OrderItem(
            UUID id,
            Integer quantity,
            BigDecimal priceAtOrderedTime
    ) {
    }

    @Builder
    public record UnavailableItem(
            UUID id,
            String name,
            Integer requestQuantity,
            Integer stock,
            OrderItemReason reason
    ) {

    }
}