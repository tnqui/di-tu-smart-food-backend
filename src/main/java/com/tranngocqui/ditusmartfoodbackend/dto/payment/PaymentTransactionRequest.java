package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentTransactionRequest(
        PaymentProvider provider,
        String orderId,
        String transactionId,
        BigDecimal amount,
        TransactionStatus status,
        Instant paidAt,
        Instant expiredAt,
        String callbackData,
        String errorMessage
) {
}