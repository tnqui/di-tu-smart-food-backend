package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import lombok.Data;

import java.util.Map;

@Data
public class PaymentCallbackData {
    private PaymentProvider provider;
    private String transactionId;
    private String rawData;
    private String signature;
    private Map<String, Object> data;
}
