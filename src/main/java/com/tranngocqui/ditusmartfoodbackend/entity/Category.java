package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationStatus;
import com.tranngocqui.ditusmartfoodbackend.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Category {
    @Id
    private UUID id;

    private String name;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
        this.createdAt = LocalDateTime.now();
    }
}