package com.tranngocqui.ditusmartfoodbackend.mapper;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleAdminResponse toRoleResponse(Role role);





}
