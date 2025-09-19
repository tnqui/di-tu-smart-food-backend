package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);

    default Page<PermissionResponse> toPermissionResponsePagination(Page<Permission> permissions){
        return permissions.map(this::toPermissionResponse);
    }


}
