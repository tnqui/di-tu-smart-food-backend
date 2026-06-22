package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MenuItemClientResponse(
        String id,
        String name,
        BigDecimal price,
        BigDecimal oldPrice,
        Integer orderCount,
        String imageUrl,
        Double rating,
        String tag,
        String description
) {
}