package com.tranngocqui.ditusmartfoodbackend.service.role;


import com.tranngocqui.ditusmartfoodbackend.dto.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleService {
    List<RoleWithoutPermissionsResponse> getAll();
    RoleWithoutPermissionsResponse create(RoleRequest request);
    RoleWithoutPermissionsResponse update(RoleRequest request);
    void delete(String role );
}
