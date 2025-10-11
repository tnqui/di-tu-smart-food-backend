package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.permission.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/permissions")
@RequiredArgsConstructor
public class PermissionAdminController {
    private final PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionAdminResponse> create(@Valid @RequestBody PermissionAdminRequest request) {
        return ApiResponse.<PermissionAdminResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<PermissionAdminResponse>> getAll() {
        return ApiResponse.<List<PermissionAdminResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @GetMapping
    ApiResponse<Page<PermissionAdminResponse>> getPermissionPagination(Pageable pageable) {
        return ApiResponse.<Page<PermissionAdminResponse>>builder()
                .result(permissionService.getPermissionPagination(pageable))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        permissionService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }



}
