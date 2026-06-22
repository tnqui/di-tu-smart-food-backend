package com.tranngocqui.ditusmartfoodbackend.mapper.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminGetResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionAdminGetResponse from(Permission permission) {
        return PermissionAdminGetResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }

    @Override
    public List<PermissionAdminGetResponse> from(List<Permission> permissions) {
        return permissions.stream().map(this::from).toList();
    }

}
