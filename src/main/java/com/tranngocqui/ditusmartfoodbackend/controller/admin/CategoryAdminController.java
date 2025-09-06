package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryAdminResponse> create(@RequestBody CategoryAdminRequest request) {
        return ApiResponse.<CategoryAdminResponse>builder()
                .result(categoryService.create(request))
                .build();
    }

//    @GetMapping
//    ApiResponse<CategoryAdminResponse> getAll() {
//        return ApiResponse.<CategoryAdminResponse>builder()
//                .result()
//                .build();
//    }

}
