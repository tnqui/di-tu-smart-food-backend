package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
@RequiredArgsConstructor
public class RoleAdminController {
    private final RoleService roleService;

    @GetMapping("/all")
    ApiResponse<List<RoleAdminResponse>> getAll() {
        return ApiResponse.<List<RoleAdminResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @GetMapping
    ApiResponse<Page<RoleAdminResponse>> getRolePagination(Pageable pageable) {
        return ApiResponse.<Page<RoleAdminResponse>>builder()
                .result(roleService.getRolePagination(pageable))
                .build();
    }

    @PostMapping
    RoleAdminResponse create(@RequestBody RoleRequest request) {
        return roleService.create(request);
    }

    @PutMapping
    ApiResponse<RoleAdminResponse> update(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleAdminResponse>builder()
                .result(roleService.update(request))
                .build();
    }

    @DeleteMapping
    ApiResponse<Void> delete(@RequestBody String id) {
        roleService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
