package com.tranngocqui.ditusmartfoodbackend.dto.admin.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record ProductAdminRequest(
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
        Set<String> categories


) {

}
