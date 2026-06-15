package com.tranngocqui.ditusmartfoodbackend.dto.external.payment;


public record PaymentWebhookResponse(
        String partnerCode,
        String orderId,
        String requestId,
        Long amount,
        String orderInfo,
        String orderType,
        Long transId,
        Integer resultCode,
        String message,
        String payType,
        Long responseTime,
        String extraData,
        String signature
) {
}