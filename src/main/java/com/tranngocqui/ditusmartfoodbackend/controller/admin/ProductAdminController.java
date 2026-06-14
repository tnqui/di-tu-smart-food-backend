package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.product.ProductAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/Products")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductAdminResponse>> create(@RequestBody ProductAdminRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productService.create(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductAdminResponse>>> get(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(productService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductAdminResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getById(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductAdminResponse>> update(@PathVariable String id, @RequestBody ProductAdminUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted Product"));
    }

}
