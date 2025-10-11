package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserAdminRequest(
        String fullName,
        String email,
        String phone,
        @NotNull
        String password,
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        boolean enabled,
        Boolean accountStatus,
        LocalDateTime lastLoginAt,
        String lastLoginIp,
        String avatarUrl,
        String language,
        Boolean twoFactorEnabled,
        String timezone,
        Set<String> roles
) {
}
