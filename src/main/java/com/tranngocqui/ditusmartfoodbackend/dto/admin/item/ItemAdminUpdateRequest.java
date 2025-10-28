package com.tranngocqui.ditusmartfoodbackend.dto.admin.item;

import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record ItemAdminUpdateRequest(
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
