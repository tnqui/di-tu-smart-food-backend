package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Boolean accountStatus;
    private String avatarUrl;
    private String language;
//    Set<RoleWithoutPermissionsResponse> roles;
}
