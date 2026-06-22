package com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request;

import lombok.Builder;

@Builder
public record PermissionAdminUpdateRequest(
        String name,
        String description
) {
}
