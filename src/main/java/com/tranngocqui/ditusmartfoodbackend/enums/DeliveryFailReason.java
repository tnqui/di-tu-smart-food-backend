package com.tranngocqui.ditusmartfoodbackend.enums;

public enum DeliveryFailReason {
    // Lỗi liên quan địa chỉ
    ADDRESS_NOT_FOUND,
    ADDRESS_INVALID,
    ADDRESS_UNCLEAR,

    // Lỗi liên quan khách hàng
    CUSTOMER_NOT_AVAILABLE,
    CUSTOMER_REFUSED,
    CUSTOMER_UNREACHABLE,
    PHONE_INVALID,

    // Lỗi liên quan giao hàng
    DELIVERY_TIMEOUT,
    DELIVERY_CANCELLED_BY_DRIVER,
    DELIVERY_CANCELLED_BY_CUSTOMER,

    // Lỗi liên quan hàng hóa
    PACKAGE_DAMAGED,
    PACKAGE_LOST,
    WRONG_ITEM,

    // Lỗi khác
    TRAFFIC_ACCIDENT,
    WEATHER_CONDITIONS,
    SYSTEM_ERROR,
    OTHER
}