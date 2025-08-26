package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleWithoutPermissionsResponse>> getAll() {
        return ApiResponse.<List<RoleWithoutPermissionsResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @PostMapping
    ApiResponse<RoleWithoutPermissionsResponse> create(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleWithoutPermissionsResponse>builder()
                .result(roleService.create(roleRequest))
                .build();
    }

    @PutMapping
    ApiResponse<RoleWithoutPermissionsResponse> update(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleWithoutPermissionsResponse>builder()
                .result(roleService.update(roleRequest))
                .build();
    }

    @DeleteMapping
    ApiResponse<Void> delete(@RequestBody String role ) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .build();
    }
}
