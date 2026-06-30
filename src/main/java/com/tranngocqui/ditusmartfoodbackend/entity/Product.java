package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer preparationTime;
    private String imageUrl;
    private Boolean active;
    private Double rating;
    private Integer orderCount;

    @ManyToOne
    @JoinColumn(name = "primary_category_Id", referencedColumnName = "id")
    private Category primaryCategory;

    public void prePersist() {
        super.prePersist();
        rating = 0.0;
        orderCount = 0;
        active = true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", active=" + active +
                '}';
    }
}
