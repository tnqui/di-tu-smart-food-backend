package com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response;

import lombok.Builder;

@Builder
public record PermissionAdminCreateResponse(
        String id,
        String name,
        String description
) {
}
