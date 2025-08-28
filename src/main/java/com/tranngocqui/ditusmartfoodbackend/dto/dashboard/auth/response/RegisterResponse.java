package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.role.response.RoleWithoutPermissionsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;
    private Boolean enabled;
    private String avatarUrl;
    private String language;
    Set<RoleWithoutPermissionsResponse> roles;
}
