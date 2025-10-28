package com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem;

public record OrderItemCheckAvailableRequest(
        String itemId,
        Integer quantity
) {
}
