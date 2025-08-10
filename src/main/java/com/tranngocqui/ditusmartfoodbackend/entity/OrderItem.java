package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_items", uniqueConstraints = @UniqueConstraint(columnNames = { "order_id", "dish_id" }))
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_at_order_time", precision = 10, scale = 2, nullable = false)
    @NotNull
    private BigDecimal priceAtOrderTime;

}