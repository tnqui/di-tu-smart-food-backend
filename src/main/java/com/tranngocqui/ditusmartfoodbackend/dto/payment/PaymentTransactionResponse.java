package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public record PaymentTransactionResponse(
        UUID id,
        Order order,
        String provider,
        String transactionId,
        BigDecimal amount,
        String status,
        Instant createdAt,
        Instant paidAt,
        Instant expiredAt,
        String callbackData,
        String errorMessage
) {
}
