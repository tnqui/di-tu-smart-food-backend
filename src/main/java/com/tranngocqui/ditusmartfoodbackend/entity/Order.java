package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount;

    private BigDecimal shippingFee;

    private String shippingAddress;

    private Double latitude;

    private Double longitude;

    private String recipientName;

    private String recipientPhone;

    private Instant paidAt;

    private String paymentMethodName;

    private String paymentTransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentTransaction transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public void prePersist() {
        super.prePersist();
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
        }
    }

}
