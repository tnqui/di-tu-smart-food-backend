package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithPermissionsResponse;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Builder
public record UserProfileResponse(
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

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        Instant createdAt,

        Set<RoleWithPermissionsResponse> roles
) {
}