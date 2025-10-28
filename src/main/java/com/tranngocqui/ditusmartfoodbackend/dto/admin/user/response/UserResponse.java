package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private boolean enabled;
    private String avatarUrl;
    private String language;
    private Boolean twoFactorEnabled;

    private Instant createdAt;
//    Set<RoleWithoutPermissionsResponse> roles;
}
