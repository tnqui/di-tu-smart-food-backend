package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    Permission toPermission(PermissionAdminRequest permissionRequest);
    PermissionAdminResponse toPermissionResponse(Permission permission);

    default Page<PermissionAdminResponse> toPermissionResponsePagination(Page<Permission> permissions){
        return permissions.map(this::toPermissionResponse);
    }




}
