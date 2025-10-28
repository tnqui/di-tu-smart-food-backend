package com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem;

import java.math.BigDecimal;

public record OrderItemCheckAvailableResponse(
        ItemValidResponse itemValidResponse,
        BigDecimal priceAtOrderedTime,
        Integer quantity
) {
}
