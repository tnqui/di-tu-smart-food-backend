package com.tranngocqui.ditusmartfoodbackend.dto.payment;

public record MoMoPaymentRequest(
        String partnerCode,
        String requestType,
        String ipnUrl,
        String redirectUrl,
        String orderId,
        Long amount,
        String orderInfo,
        String requestId,
        String extraData,
        String signature,
        String lang
) {
}