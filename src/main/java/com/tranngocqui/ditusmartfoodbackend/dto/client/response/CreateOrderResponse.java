package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateOrderResponse(
        UUID id,
        String userId,
        List<OrderItemResponse> items,
        OrderStatus status,
        BigDecimal totalAmount,
        BigDecimal shippingFee,
        Instant createdAt,
        String shippingAddress,
        String recipientName,
        String recipientPhone,
        String paymentMethodName
) {
}