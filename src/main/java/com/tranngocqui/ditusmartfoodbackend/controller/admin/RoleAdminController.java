package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/roles")
@RequiredArgsConstructor
public class RoleAdminController {
    private final RoleService roleService;

    @GetMapping()
    ApiResponse<Page<RoleAdminResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<RoleAdminResponse>>builder()
                .result(roleService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<RoleAdminResponse> getById(@PathVariable String id) {
        return ApiResponse.<RoleAdminResponse>builder()
                .result(roleService.getById(id))
                .build();
    }

    @PostMapping
    ApiResponse<RoleAdminResponse> create(@RequestBody RoleAdminRequest request) {
        return ApiResponse.<RoleAdminResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @PatchMapping("/{id}")
    ApiResponse<RoleAdminResponse> update(@PathVariable String id, @RequestBody RoleAdminRequest request) {
        return ApiResponse.<RoleAdminResponse>builder()
                .result(roleService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        roleService.deleteById(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted role")
                .build();
    }
}
