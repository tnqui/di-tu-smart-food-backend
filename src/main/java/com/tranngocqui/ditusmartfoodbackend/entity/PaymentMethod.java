package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentMethod {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PaymentProvider name;

    private String description;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}
