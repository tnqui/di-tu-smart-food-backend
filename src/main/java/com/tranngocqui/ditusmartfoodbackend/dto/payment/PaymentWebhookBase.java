package com.tranngocqui.ditusmartfoodbackend.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class PaymentWebhookBase {
    private String orderId;
}