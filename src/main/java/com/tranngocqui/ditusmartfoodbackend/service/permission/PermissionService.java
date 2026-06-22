package com.tranngocqui.ditusmartfoodbackend.service.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminGetResponse;

import java.util.List;

public interface PermissionService {

    void save(PermissionAdminCreateRequest request);

    PermissionAdminGetResponse findById(String id);

    List<PermissionAdminGetResponse> findAll();

    void update(String id, PermissionAdminUpdateRequest request);

    void softDeleteById(String id);
}
