package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.OrderItemReason;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderItemStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@Where(clause = "deleted = false")
@NoArgsConstructor
@SuperBuilder
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private UUID productId;

    private String productName;

    private Integer quantity;

    private Integer stock;

    private BigDecimal priceAtOrderedTime;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Enumerated(EnumType.STRING)
    private OrderItemReason reason;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status;

    @Override
    public String toString() {
        return "OrderItem{" +
                "order=" + order +
                ", priceAtOrderedTime=" + priceAtOrderedTime +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                '}';
    }
}
