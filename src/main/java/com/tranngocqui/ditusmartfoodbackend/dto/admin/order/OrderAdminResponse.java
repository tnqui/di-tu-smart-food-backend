package com.tranngocqui.ditusmartfoodbackend.dto.admin.order;

import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
public record OrderAdminResponse(
        String id,
        String userId,
        OrderStatus status,
        BigDecimal totalAmount,
        BigDecimal shippingFee,
        String shippingAddress,
        Double latitude,
        Double longitude,
        String recipientName,
        String recipientPhone,
        Instant paidAt,
        String paymentMethodId,
        String paymentMethodName,
        String deliveryMethodId,
        String deliveryMethodName,
        String paymentTransactionId,
        List<OrderItemResponse> items,
        Instant createdAt,
        Instant updatedAt,
        String message
) {
}
