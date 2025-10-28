package com.tranngocqui.ditusmartfoodbackend.dto.admin.item;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record ItemAdminResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Integer preparationTime,
        Integer orderCount,
        String imageUrl,
        Boolean enabled,
        Instant createdAt,
        Instant updatedAt,
        Set<CategoryResponse> categories
) {
}
