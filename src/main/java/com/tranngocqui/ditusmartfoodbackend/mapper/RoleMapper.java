package com.tranngocqui.ditusmartfoodbackend.mapper;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleAdminRequest request);
    RoleAdminResponse toRoleAdminResponse(Role role);

    @Mapping(target = "permissions",  ignore = true)
    void update(RoleAdminRequest request, @MappingTarget Role role);
}
