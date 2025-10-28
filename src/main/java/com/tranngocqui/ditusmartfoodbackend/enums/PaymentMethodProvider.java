package com.tranngocqui.ditusmartfoodbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethodProvider {
    MOMO("MoMo"),
    ZALOPAY("Zalo Pay"),
    COD("COD"),
    BANKING("Banking"),
    ;
    private final String fullName;
}
