package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentQueryResponse(
        PaymentStatus status,
        String transactionId,
        BigDecimal amount,
        String errorMessage,
        Instant paidAt
) {
}