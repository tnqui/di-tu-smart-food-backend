package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        String id,
        Integer quantity,
        BigDecimal priceAtOrderedTime,
        String menuItemName
) {
}