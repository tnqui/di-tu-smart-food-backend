package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PaymentResponse {
    private boolean success;
    private String paymentUrl;
    private String transactionId;
    private String errorMessage;
    private PaymentProvider provider;
    private String status;         // SUCCESS / PENDING / FAILED
    private String redirectUrl;    // Nếu cần redirect khách (QR, payment page)
    private String message;
    private Map<String, Object> additionalData;
}
