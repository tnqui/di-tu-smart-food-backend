package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    @ManyToMany
    private Set<Permission> permissions;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}