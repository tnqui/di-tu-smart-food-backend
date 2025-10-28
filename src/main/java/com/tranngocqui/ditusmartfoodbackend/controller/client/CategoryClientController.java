package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.service.application.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryClientController {
    private final CategoryService categoryService;

//    @GetMapping("/all")
//    ApiResponse<List<CategoryClientResponse>> getAll() {
//        return ApiResponse.<List<CategoryClientResponse>>builder()
//                .result(categoryService.getCategoriesPagination())
//                .build();
//    }


}
