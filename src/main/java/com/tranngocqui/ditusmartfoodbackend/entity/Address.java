package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Address {
    @Id
    private UUID id;

    private String label;
    private String fullAddress;
    private Double latitude;
    private Double longitude;
    private boolean defaultAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}
