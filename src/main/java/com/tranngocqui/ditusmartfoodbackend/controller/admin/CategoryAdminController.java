package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryAdminResponse>> create(@RequestBody CategoryAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(request)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<CategoryAdminResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryAdminResponse>>> getCategoriesPagination(
            @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {

        Page<CategoryAdminResponse> categories = categoryService.getCategoriesPagination(pageable);
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<CategoryAdminResponse>> update(@PathVariable String id, @RequestBody CategoryAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted"));
    }
}
