package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.dish.DishAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.mapper.DishMapper;
import com.tranngocqui.ditusmartfoodbackend.service.dish.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dishes")
@RequiredArgsConstructor
public class DishAdminController {
    private final DishService dishService;

    @PostMapping
    ApiResponse<DishAdminResponse> create(@RequestBody DishAdminRequest request) {
        return ApiResponse.<DishAdminResponse>builder()
                .result(dishService.create(request))
                .build();
    }

}
