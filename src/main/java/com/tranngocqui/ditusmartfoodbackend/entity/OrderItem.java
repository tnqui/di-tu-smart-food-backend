package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter(AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private UUID productId;

    private Integer quantity;

    private BigDecimal priceAtOrderedTime;

    @Column(columnDefinition = "TEXT")
    @Builder.Default
    private String note = "";
}
