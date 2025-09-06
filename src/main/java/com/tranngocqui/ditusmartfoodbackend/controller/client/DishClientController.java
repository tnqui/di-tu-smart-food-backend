package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.PagedResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.DishClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Dish;
import com.tranngocqui.ditusmartfoodbackend.service.dish.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishClientController {
    private final DishService dishService;

    @GetMapping
    public ApiResponse<PagedResponse<DishClientResponse>> getDishesPagination(
            @RequestParam(required = false) String categoryName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        Page<DishClientResponse> dishPage = dishService.getDishesPagination(categoryName, page, limit);

        PagedResponse<DishClientResponse> response = PagedResponse.<DishClientResponse>builder()
                .items(dishPage.getContent())
                .currentPage(dishPage.getNumber() + 1)
                .totalPages(dishPage.getTotalPages())
                .totalItems(dishPage.getTotalElements())
                .build();

        return ApiResponse.<PagedResponse<DishClientResponse>>builder()
                .result(response)
                .build();
    }
}
