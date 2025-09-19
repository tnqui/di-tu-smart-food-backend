package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {
    private final CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryAdminResponse> create(@RequestBody CategoryAdminRequest request) {
        return ApiResponse.<CategoryAdminResponse>builder()
                .result(categoryService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<CategoryAdminResponse>> getCategoriesPagination(
            @PageableDefault(size = 10, page = 0, sort = "createdAt") Pageable pageable) {

        Page<CategoryAdminResponse> categories = categoryService.getCategoriesPagination(pageable);

        return ApiResponse.<Page<CategoryAdminResponse>>builder()
                .result(categories)
                .build();

    }


//    @GetMapping
//    ApiResponse<CategoryAdminResponse> getAll() {
//        return ApiResponse.<CategoryAdminResponse>builder()
//                .result()
//                .build();
//    }

}
