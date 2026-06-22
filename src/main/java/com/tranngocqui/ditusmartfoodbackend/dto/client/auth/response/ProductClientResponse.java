package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import java.math.BigDecimal;

public record ProductClientResponse(

        String id,
        String name,
        BigDecimal price,
        BigDecimal oldPrice,
        Integer orderCount,
        String imageUr,
        Double rating,
        String tag,
        String description) {
}
