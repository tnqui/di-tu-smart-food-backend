package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethodProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class PaymentMethod extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PaymentMethodProvider shortName;
    private String fullName;
}
