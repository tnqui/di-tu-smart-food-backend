package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Double rating = 5.0;
    @Builder.Default
    private String tag = "New";
    private Integer stock;
    private Integer preparationTime;
    @Builder.Default
    private Integer orderCount = 0;

    private String imageUrl;

    @Builder.Default
    private Boolean isFeatured = false;

    @Builder.Default
    private Integer displayOrder = 999;

    @Builder.Default
    private Boolean isAvailable = true;

    @ManyToMany
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "primary_category_Id", referencedColumnName = "id")
    private Category primaryCategory;

}
