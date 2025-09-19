package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private Double rating;
    private String tag;
    private Integer stock;
    private Integer preparationTime;
    private Integer orderCount;
    private String imageUrl;
    private Boolean enabled;
    private Boolean isFeatured = false;
    private Integer displayOrder = 999;
    private Boolean isAvailable = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "primary_category_Id", referencedColumnName = "id")
    private Category primaryCategory;

}
