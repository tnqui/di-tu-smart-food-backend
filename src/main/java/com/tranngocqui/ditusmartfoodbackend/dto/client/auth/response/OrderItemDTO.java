package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemDTO(
        Long menuItemId,
        String menuItemName,
        String name,
        int quantity,
        BigDecimal priceAtOrderTime
) {
}