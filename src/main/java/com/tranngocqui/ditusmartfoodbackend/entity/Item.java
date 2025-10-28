package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Item extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Double rating = 5.0;
    private String tag = "New";
    private Integer stock;
    private Integer preparationTime;
    private Integer orderCount = 0;

    private String imageUrl;

    private Boolean isFeatured = false;

    private Integer displayOrder = 999;

    private Boolean isAvailable = true;

    @ManyToMany
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "primary_category_Id", referencedColumnName = "id")
    private Category primaryCategory;

}
