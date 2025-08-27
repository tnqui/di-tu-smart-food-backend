package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.request.PermissionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.permission.response.PermissionResponse;
import com.tranngocqui.ditusmartfoodbackend.service.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .build();
    }

}
