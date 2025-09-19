package com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemAdminRequest {
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
    private Set<String> categories;
}
