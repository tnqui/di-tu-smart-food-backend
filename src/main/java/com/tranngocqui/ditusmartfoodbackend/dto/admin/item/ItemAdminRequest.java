package com.tranngocqui.ditusmartfoodbackend.dto.admin.item;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record ItemAdminRequest(
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
