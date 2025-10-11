package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Permission {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}
