package com.tranngocqui.ditusmartfoodbackend.service.role;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleAdminResponse> getAll();

    RoleAdminResponse create(RoleRequest request);

    RoleAdminResponse update(RoleRequest request);

    void delete(String id);

    Optional<Role> findByName(String name);

    Page<RoleAdminResponse> getRolePagination(Pageable pageable);
}
