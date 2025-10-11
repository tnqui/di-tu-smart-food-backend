package com.tranngocqui.ditusmartfoodbackend.service.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    PermissionAdminResponse create(PermissionAdminRequest request);
    List<PermissionAdminResponse> getAll();
    void delete(String name);
    Page<PermissionAdminResponse> getPermissionPagination(Pageable pageable);
}
