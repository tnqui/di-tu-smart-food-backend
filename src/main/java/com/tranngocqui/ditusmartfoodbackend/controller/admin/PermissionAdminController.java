package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.permission.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    ApiResponse<Page<PermissionAdminResponse>> getPermissionPagination(Pageable pageable) {
        return ApiResponse.<Page<PermissionAdminResponse>>builder()
                .result(permissionService.getPermissionPagination(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<PermissionAdminResponse> getPermission(@PathVariable String id) {
        return ApiResponse.<PermissionAdminResponse>builder()
                .result(permissionService.getById(id))
                .build();

    }

    @PatchMapping("/{id}")
    ApiResponse<PermissionAdminResponse> update(@Valid @PathVariable String id, @RequestBody PermissionAdminRequest request) {
        return ApiResponse.<PermissionAdminResponse>builder()
                .result(permissionService.update(id, request))
                .build();
    }


    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        permissionService.deleteById(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted permission")
                .build();
    }

}
