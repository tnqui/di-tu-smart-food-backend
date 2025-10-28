package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request;

import java.util.Set;

public record RoleAdminRequest(
        String name,
        String description,
        Set<String> permissions
) {
}
