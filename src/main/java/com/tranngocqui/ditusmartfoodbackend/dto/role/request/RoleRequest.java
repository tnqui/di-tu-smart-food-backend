package com.tranngocqui.ditusmartfoodbackend.dto.role.request;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import lombok.*;

import java.time.LocalDateTime;
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
