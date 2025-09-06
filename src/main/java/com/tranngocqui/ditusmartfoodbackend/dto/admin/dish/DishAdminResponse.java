package com.tranngocqui.ditusmartfoodbackend.dto.admin.dish;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Category;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishAdminResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer preparationTime;
    private Integer orderCount;
    private String imageUrl;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<CategoryResponse> categories;
}
