package com.tranngocqui.ditusmartfoodbackend.service.role;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleWithoutPermissionsResponse> getAll();

    RoleWithoutPermissionsResponse create(RoleRequest request);

    RoleWithoutPermissionsResponse update(RoleRequest request);

    void delete(String role);

    Optional<Role> findByName(String name);

    Page<RoleResponse> getRolePagination(Pageable pageable);
}
