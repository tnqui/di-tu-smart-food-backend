package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;


import java.time.Instant;
import java.util.UUID;

public record UserAdminResponse(
        UUID id,

        String fullName,

        String email,

        String phone,

        Boolean isEmailVerified,

        Boolean isPhoneVerified,

        boolean enabled,

        Integer loginAttempts,

        String lastLoginIp,

        Instant lastLoginAt,

        String avatarUrl,

        String language,

        String timezone,

        Boolean twoFactorEnabled,

        Boolean deleted,

        Instant deletedAt,

        Instant createdAt,

        Instant updatedAt
) {
}
