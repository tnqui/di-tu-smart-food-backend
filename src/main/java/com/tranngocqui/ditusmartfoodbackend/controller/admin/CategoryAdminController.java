package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<ApiResponse<CategoryAdminResponse>> create(@RequestBody CategoryAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(request)));
    }

    @GetMapping("{id}")
    ResponseEntity<ApiResponse<CategoryAdminResponse>> get(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getById(id)));
    }

    @GetMapping
    ResponseEntity<ApiResponse<Page<CategoryAdminResponse>>> getPagination(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategoriesPagination(pageable)));
    }

    @PatchMapping("{id}")
    ResponseEntity<ApiResponse<CategoryAdminResponse>> update(@PathVariable String id, @RequestBody CategoryAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, request)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted"));
    }
}
