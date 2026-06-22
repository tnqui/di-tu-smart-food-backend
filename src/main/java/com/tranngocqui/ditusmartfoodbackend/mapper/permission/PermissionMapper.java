package com.tranngocqui.ditusmartfoodbackend.mapper.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminGetResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    PermissionAdminGetResponse from(Permission permission);

    List<PermissionAdminGetResponse> from(List<Permission> permissions);
}
