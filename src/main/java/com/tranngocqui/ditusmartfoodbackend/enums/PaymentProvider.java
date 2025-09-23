package com.tranngocqui.ditusmartfoodbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentProvider {
    ZALOPAY("zalopay", "ZaloPay"),
    MOMO("momo", "MoMo"),
    VNPAY("vnpay", "VNPay"),
    CASH("cash", "Cash on Delivery"),
    BANK_TRANSFER("bank_transfer", "Bank Transfer"),
    COD("cod", "COD");
    private final String code;
    private final String displayName;
}