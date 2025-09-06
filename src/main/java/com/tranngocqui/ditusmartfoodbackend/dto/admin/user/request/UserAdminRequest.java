package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminRequest {
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
    private Set<String> roles;
}
