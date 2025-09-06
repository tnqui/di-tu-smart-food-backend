package com.tranngocqui.ditusmartfoodbackend.service.role;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithoutPermissionsResponse;

import java.util.List;

public interface RoleService {
    List<RoleWithoutPermissionsResponse> getAll();
    RoleWithoutPermissionsResponse create(RoleRequest request);
    RoleWithoutPermissionsResponse update(RoleRequest request);
    void delete(String role );
}
