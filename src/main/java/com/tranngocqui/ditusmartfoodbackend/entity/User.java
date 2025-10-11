package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE users SET deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id = ?::uuid")
@Table(name = "users")
public class User extends BaseEntity {
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;
    @Builder.Default
    private Boolean isEmailVerified = false;
    @Builder.Default
    private Boolean isPhoneVerified = false;
    @Builder.Default
    private boolean enabled = true;
    @Builder.Default
    private Integer loginAttempts = 0;

    private LocalDateTime lastLoginAt;

    private String lastLoginIp;

    private String avatarUrl;

    private String language;

    private String timezone;

    private String twoFactorSecret;

    @Builder.Default
    private Boolean twoFactorEnabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jwt> jwts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles;

}