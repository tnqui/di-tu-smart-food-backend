package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.MenuItemClientResponse;
import com.tranngocqui.ditusmartfoodbackend.service.menuitem.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemClientController {
    private final MenuItemService menuItemService;

    @GetMapping
    public ApiResponse<Page<MenuItemClientResponse>> getMenuItemsByCategoryId(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        int safePage = (page == null || page < 1) ? 1 : page;
        Pageable pageable = PageRequest.of(safePage - 1, size);

        Page<MenuItemClientResponse> menuItems = menuItemService.getMenuItemPaginationByCategoryId(pageable, categoryId);

        return ApiResponse.<Page<MenuItemClientResponse>>builder()
                .result(menuItems)
                .build();
    }
}
