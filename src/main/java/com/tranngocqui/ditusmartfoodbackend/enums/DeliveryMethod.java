package com.tranngocqui.ditusmartfoodbackend.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryMethod {
    GHTK("Giao Hàng Tiết Kiệm"),
    GRAB("GRAB"),
    BE("BE"),
    COD("COD"),
    PICKUP("PICKUP"),
    IN_HOUSE("IN_HOUSE"),
    @JsonEnumDefaultValue UNKNOWN("UNKNOWN");

    private final String fullName;
}
