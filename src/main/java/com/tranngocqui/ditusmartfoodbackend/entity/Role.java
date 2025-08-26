package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role {
    @Id
    private String name;
    private String description;
    private LocalDateTime createdAt;

    @ManyToMany
    private Set<Permission> permissions;

}