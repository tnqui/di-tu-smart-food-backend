package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithoutPermissionsResponse;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID id,
        String fullName,
        String email,
        String phone,
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        Boolean enabled,
        String avatarUrl,
        String language,
        Set<RoleWithoutPermissionsResponse> roles
) {
}