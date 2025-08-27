package com.tranngocqui.ditusmartfoodbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private Boolean accountStatus;
    private Integer loginAttempts;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
    private String avatarUrl;
    private String language;
    private String timezone;

    private String twoFactorSecret;
    private boolean twoFactorEnabled = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToMany
    private Set<Role> roles;


//
//    public User() {
//        this.isEmailVerified = false;
//        this.isPhoneVerified = false;
//        this.accountStatus = true;
//        this.loginAttempts = 0;
//        this.language = "vi";
//        this.timezone = "Asia/Ho_Chi_Minh";
//    }
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }

}