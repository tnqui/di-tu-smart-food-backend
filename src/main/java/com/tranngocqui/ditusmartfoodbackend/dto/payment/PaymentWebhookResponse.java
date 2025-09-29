package com.tranngocqui.ditusmartfoodbackend.dto.payment;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentWebhookResponse extends PaymentWebhookBase {
    String partnerCode;
    String orderId;
    String requestId;
    Long amount;
    String orderInfo;
    String orderType;
    Long transId;
    Integer resultCode;
    String message;
    String payType;
    Long responseTime;
    String extraData;
    String signature;
}
