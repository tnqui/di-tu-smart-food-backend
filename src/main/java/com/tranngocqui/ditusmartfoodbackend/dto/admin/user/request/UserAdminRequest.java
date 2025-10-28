package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Set;

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
        Instant lastLoginAt,
        String lastLoginIp,
        String avatarUrl,
        String language,
        Boolean twoFactorEnabled,
        String timezone,
        Set<String> roles
) {
}
