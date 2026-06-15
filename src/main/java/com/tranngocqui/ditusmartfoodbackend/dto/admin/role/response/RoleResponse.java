package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record RoleResponse(
        String name,
        String description,
        Instant createdAt
) {
}