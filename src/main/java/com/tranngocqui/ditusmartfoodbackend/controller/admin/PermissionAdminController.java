package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.request.PermissionAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.permission.response.PermissionAdminGetResponse;
import com.tranngocqui.ditusmartfoodbackend.service.permission.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/permissions")
@RequiredArgsConstructor
public class PermissionAdminController {
    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody PermissionAdminCreateRequest request) {
        permissionService.save(request);
        return ResponseEntity.ok(ApiResponse.ok("Successfully created permission"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionAdminGetResponse>> getPermission(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.ok(permissionService.findById(id)));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PermissionAdminGetResponse>>> getAllPermissions() {
        return ResponseEntity.ok(ApiResponse.ok(permissionService.findAll()));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PermissionAdminGetResponse>> update(@PathVariable String id, @Valid @RequestBody PermissionAdminUpdateRequest request) {
        permissionService.update(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Successfully updated permission"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        permissionService.softDeleteById(id);
        return ResponseEntity.ok(ApiResponse.ok("Successfully deleted permission"));
    }

}
