package com.tranngocqui.ditusmartfoodbackend.dto.admin.product;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record ProductAdminResponse(
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
