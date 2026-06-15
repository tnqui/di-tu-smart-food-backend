package com.tranngocqui.ditusmartfoodbackend.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductClientController {
//    private final ProductService productService;
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<Page<ProductClientResponse>>> getMenuProductsByCategoryId(
//            @RequestParam(required = false) String categoryId,
//            @RequestParam(required = false) Integer page,
//            @RequestParam(defaultValue = "10") Integer size) {
//
//        int safePage = (page == null || page < 1) ? 1 : page;
//        Pageable pageable = PageRequest.of(safePage - 1, size);
//
//        return ResponseEntity.ok(ApiResponse.success(productService.getPagination(pageable, categoryId)));
//    }
}
