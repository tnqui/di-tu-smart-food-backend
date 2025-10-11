package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DeliveryMethod {
    @Id
    private UUID id;

    private String name;
    private String description;
    private Integer time;
    private BigDecimal price;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}

