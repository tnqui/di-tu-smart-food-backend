package com.tranngocqui.ditusmartfoodbackend.dto.role.response;

import com.tranngocqui.ditusmartfoodbackend.dto.permission.response.PermissionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleWithPermissionsResponse {
    private String name;
    private String description;
    private Set<PermissionResponse> permissions;
}
