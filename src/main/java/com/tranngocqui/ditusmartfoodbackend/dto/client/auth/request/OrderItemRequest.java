package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.request;

import java.math.BigDecimal;

public record OrderItemRequest(
        String itemId,
        Integer quantity,
        BigDecimal priceAtOrderTime
) {
}
