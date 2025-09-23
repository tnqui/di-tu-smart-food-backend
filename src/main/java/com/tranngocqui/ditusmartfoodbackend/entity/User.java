package com.tranngocqui.ditusmartfoodbackend.entity;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
    private String password;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private boolean enabled;
    private Integer loginAttempts;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
    private String avatarUrl;
    private String language;
    private String timezone;

    private String twoFactorSecret;
    private Boolean twoFactorEnabled = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jwt> jwts = new ArrayList<>();

    @ManyToMany
    private Set<Role> roles;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = Generators.timeBasedEpochRandomGenerator().generate();
        }
    }
}