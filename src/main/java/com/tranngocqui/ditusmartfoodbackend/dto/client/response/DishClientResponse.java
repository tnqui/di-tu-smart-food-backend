package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishClientResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private String imageUrl;
    private Double rating;
    private String tag;
    private String description;
    private Set<CategoryClientResponse> categories;
}
