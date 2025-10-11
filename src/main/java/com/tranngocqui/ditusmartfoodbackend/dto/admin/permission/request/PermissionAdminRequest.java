package com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request;

import jakarta.validation.constraints.NotBlank;

public record PermissionAdminRequest(
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String name,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String description
) {
}
