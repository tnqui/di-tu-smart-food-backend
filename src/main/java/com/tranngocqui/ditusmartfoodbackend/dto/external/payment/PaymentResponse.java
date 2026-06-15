package com.tranngocqui.ditusmartfoodbackend.dto.external.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import lombok.Builder;

import java.util.Map;

@Builder
public record PaymentResponse(
        boolean success,
        String paymentUrl,
        String transactionId,
        String errorMessage,
        PaymentProvider provider,
        String status,                 // SUCCESS / PENDING / FAILED
        String redirectUrl,            // QR, payment page...
        String message,
        Map<String, Object> additionalData
) {
}