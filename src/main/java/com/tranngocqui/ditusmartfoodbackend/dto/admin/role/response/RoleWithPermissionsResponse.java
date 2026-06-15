package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import lombok.Builder;

import java.util.Set;

@Builder
public record RoleWithPermissionsResponse(
        String name,
        String description,
        Set<PermissionAdminResponse> permissions
) {
}