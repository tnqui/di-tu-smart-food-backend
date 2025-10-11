package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;
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
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime lastLoginAt,
        String avatarUrl,
        String language,
        String timezone,
        Boolean twoFactorEnabled,
//        Set<String> roles, // hoặc List<RoleResponse> nếu cần chi tiết
        Boolean deleted,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime deletedAt,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime updatedAt
) {
}
