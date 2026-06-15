package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response;

import lombok.Builder;

@Builder
public record RoleWithoutPermissionsResponse(
        String name,
        String description
) {
}