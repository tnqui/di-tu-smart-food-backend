package com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private String name;
    private String description;
    private Set<String> permissions;
}
