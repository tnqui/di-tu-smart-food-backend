package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "order_items", uniqueConstraints = @UniqueConstraint(columnNames = {"order_id", "menu_item_id"}))
public class OrderItem {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    // @NotNull
    // @Column(nullable = false)
    private Integer quantity;

    // @Column(name = "price_at_order_time", precision = 10, scale = 2, nullable = false)
    // @NotNull
    private BigDecimal priceAtOrderTime;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}
