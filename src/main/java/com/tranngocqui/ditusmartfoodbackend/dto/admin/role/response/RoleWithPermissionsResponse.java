package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response;

import lombok.Builder;

@Builder
public record RoleWithPermissionsResponse(
        String name,
        String description
) {
}