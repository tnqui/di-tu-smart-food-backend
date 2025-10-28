package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record UserAdminProfileResponse(
        UUID id,
        String fullName,
        String email,
        String phone,
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        boolean enabled,
        String avatarUrl,
        String language,
        Boolean twoFactorEnabled,
        Instant createdAt
) {
}
