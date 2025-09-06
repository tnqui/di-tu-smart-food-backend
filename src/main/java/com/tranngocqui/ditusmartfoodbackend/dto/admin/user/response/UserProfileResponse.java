package com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithPermissionsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private Set<RoleWithPermissionsResponse> roles;
}
