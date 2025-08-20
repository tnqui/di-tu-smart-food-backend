package com.tranngocqui.ditusmartfoodbackend.dto.role.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tranngocqui.ditusmartfoodbackend.dto.permission.response.PermissionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleWithoutPermissionsResponse {
    private String name;
    private String description;
}
