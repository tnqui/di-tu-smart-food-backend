package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.request.PermissionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.response.PermissionResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
