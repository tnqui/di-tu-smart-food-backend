package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.CategoryClientResponse;
import com.tranngocqui.ditusmartfoodbackend.mapper.CategoryMapper;
import com.tranngocqui.ditusmartfoodbackend.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryClientController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    ApiResponse<List<CategoryClientResponse>> getAll() {
        return ApiResponse.<List<CategoryClientResponse>>builder()
                .result(categoryService.getAll())
                .build();
    }



}
