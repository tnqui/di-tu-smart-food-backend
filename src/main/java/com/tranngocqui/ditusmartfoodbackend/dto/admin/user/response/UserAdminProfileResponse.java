package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithPermissionsResponse;

import java.time.LocalDateTime;
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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        Set<RoleWithPermissionsResponse> roles

) {
}
