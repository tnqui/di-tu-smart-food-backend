package com.tranngocqui.ditusmartfoodbackend.enums;

public enum OrderStatus {
    PENDING,
    CUSTOMER_CONFIRMED,
    WAITING_FOR_PAYMENT,
    PAID,
    RESTAURANT_CONFIRMED,
    PREPARING,
    READY_FOR_DELIVERY,
    DELIVERING,
    ARRIVED,
    DELIVERED,
    DELIVERY_FAILED,
    CANCELLED,
    REFUNDED;

    public boolean canCancel() {
        return this == PENDING || this == CUSTOMER_CONFIRMED || this == WAITING_FOR_PAYMENT || this == PAID || this == RESTAURANT_CONFIRMED;
    }


}
