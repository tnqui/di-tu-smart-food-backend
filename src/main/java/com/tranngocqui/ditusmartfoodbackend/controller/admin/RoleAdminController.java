package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.request.RoleAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.role.response.RoleAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/roles")
@RequiredArgsConstructor
public class RoleAdminController {
    private final RoleService roleService;
    @GetMapping()
    public ResponseEntity<ApiResponse<Page<RoleAdminResponse>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(roleService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleAdminResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(roleService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleAdminResponse>> create(@RequestBody RoleAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(roleService.create(request)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleAdminResponse>> update(@PathVariable String id, @RequestBody RoleAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(roleService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        roleService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted role"));
    }
}
