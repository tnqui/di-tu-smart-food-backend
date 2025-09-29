package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoMoPaymentRequest {
    String partnerCode;
    String requestType;
    String ipnUrl;
    String redirectUrl;
    String orderId;
    Long amount;
    String orderInfo;
    String requestId;
    String extraData;
    String signature;
    String lang;
}
