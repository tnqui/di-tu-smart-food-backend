package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record UserAdminUpdateRequest(
         String fullName,
         String email,
         String phone,
         String password,
         Boolean isEmailVerified,
         Boolean isPhoneVerified,
         Boolean accountStatus,
         Integer loginAttempts,
         Instant lastLoginAt,
         String lastLoginIp,
         String avatarUrl,
         String language,
         Boolean twoFactorEnabled,
         String timezone,
         Set<String> roles
) {
}
