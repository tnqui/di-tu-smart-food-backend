package com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod;

import lombok.Data;

@Data
public class PaymentMethodRequest {
    private String id;
    private String name;
    private String description;
}
