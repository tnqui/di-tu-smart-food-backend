package com.tranngocqui.ditusmartfoodbackend.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    MOMO("MoMo"),
    COD("COD - Cash On Delivery"),
    BANK_TRANSFER("Banking")
    ;

    private final String provider;

    PaymentMethod(String provider) {
        this.provider = provider;
    }
}

