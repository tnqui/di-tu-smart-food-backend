package com.tranngocqui.ditusmartfoodbackend.service.permission;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.request.PermissionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String name);
}
