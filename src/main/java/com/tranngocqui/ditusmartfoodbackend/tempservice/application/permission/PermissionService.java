package com.tranngocqui.ditusmartfoodbackend.tempservice.application.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    PermissionAdminResponse create(PermissionAdminRequest request);

    List<PermissionAdminResponse> getAll();

    PermissionAdminResponse getById(String id);

    void deleteById(String id);

    PermissionAdminResponse update(String id, PermissionAdminRequest request);

    Page<PermissionAdminResponse> getPermissionPagination(Pageable pageable);
}
