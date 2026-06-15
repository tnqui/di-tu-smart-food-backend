package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record UserResponse(
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
//        Set<RoleWithoutPermissionsResponse> roles
) {
}