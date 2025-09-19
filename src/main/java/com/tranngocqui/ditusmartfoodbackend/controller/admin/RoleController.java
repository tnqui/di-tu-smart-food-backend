package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleRequest;
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
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/all")
    ApiResponse<List<RoleWithoutPermissionsResponse>> getAll() {
        return ApiResponse.<List<RoleWithoutPermissionsResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @GetMapping
    ApiResponse<Page<RoleResponse>> getRolePagination(Pageable pageable) {
        return ApiResponse.<Page<RoleResponse>>builder()
                .result(roleService.getRolePagination(pageable))
                .build();
    }

    //    @PostMapping
//    ApiResponse<RoleWithoutPermissionsResponse> create(@RequestBody RoleRequest roleRequest) {
//        return ApiResponse.<RoleWithoutPermissionsResponse>builder()
//                .result(roleService.create(roleRequest))
//                .build();
//    }
    @PostMapping
    RoleWithoutPermissionsResponse create(@RequestBody RoleRequest roleRequest) {
        return roleService.create(roleRequest);
//
    }


    @PutMapping
    ApiResponse<RoleWithoutPermissionsResponse> update(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleWithoutPermissionsResponse>builder()
                .result(roleService.update(roleRequest))
                .build();
    }

    @DeleteMapping
    ApiResponse<Void> delete(@RequestBody String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder()
                .build();
    }
}
