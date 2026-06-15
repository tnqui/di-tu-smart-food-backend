package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/permissions")
@RequiredArgsConstructor
public class PermissionAdminController {
//    private final PermissionService permissionService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<PermissionAdminResponse>> create(@Valid @RequestBody PermissionAdminRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(permissionService.create(request)));
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<Page<PermissionAdminResponse>>> getPermissionPagination(Pageable pageable) {
//        return ResponseEntity.ok(ApiResponse.success(permissionService.getPermissionPagination(pageable)));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<PermissionAdminResponse>> getPermission(@PathVariable String id) {
//        return ResponseEntity.ok(ApiResponse.success(permissionService.getById(id)));
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<ApiResponse<PermissionAdminResponse>> update(@PathVariable String id, @Valid @RequestBody PermissionAdminRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(permissionService.update(id, request)));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
//        permissionService.deleteById(id);
//        return ResponseEntity.ok(ApiResponse.success("Successfully deleted permission"));
//    }

}
