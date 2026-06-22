package com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PermissionAdminGetResponse(
        UUID id,
        String name,
        String description
) {
}
