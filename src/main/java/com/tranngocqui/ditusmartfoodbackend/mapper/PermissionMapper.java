package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissionMapper {
    Permission toPermission(PermissionAdminRequest permissionRequest);

    PermissionAdminResponse toPermissionAdminResponse(Permission permission);

    void update(PermissionAdminRequest request, @MappingTarget Permission permission);

    default Page<PermissionAdminResponse> toPermissionResponsePagination(Page<Permission> permissions) {
        return permissions.map(this::toPermissionAdminResponse);
    }

}
