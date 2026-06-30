package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Where(clause = "deleted = false")
@NoArgsConstructor
public class Order extends BaseEntity {
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount;

    private BigDecimal lineItemTotal;

    private BigDecimal shippingFee;

    private String customerAddress;

    private Double latitude;

    private Double longitude;

    private Double distance;

    private String recipientName;

    private String recipientPhone;

    private Instant paidAt;

    private String note;

    private String failedReason;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentTransaction transaction;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public void prePersist() {
        super.prePersist();
        orderId = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("ddMMyyyy"))
                + "ORDER"
                + getId().toString()
                .replace("-", "")
                .substring(0, 8)
                .toUpperCase();
        status = OrderStatus.PENDING;
    }

}
