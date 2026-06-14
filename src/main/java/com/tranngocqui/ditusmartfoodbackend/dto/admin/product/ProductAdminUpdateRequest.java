package com.tranngocqui.ditusmartfoodbackend.dto.admin.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductAdminUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        BigDecimal oldPrice,
        Double rating,
        String tag,
        Integer stock,
        Integer preparationTime,
        Integer orderCount,

        String imageUrl,

        Boolean isFeatured,

        Integer displayOrder,

        Boolean isAvailable,

        List<String> categories,

        String primaryCategory
) {
}
