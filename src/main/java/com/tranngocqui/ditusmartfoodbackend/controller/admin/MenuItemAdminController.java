package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.category.CategoryAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.menuitem.MenuItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.menuitem.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/menu-items")
@RequiredArgsConstructor
public class MenuItemAdminController {
    private final MenuItemService menuItemService;

    @PostMapping
    public ApiResponse<MenuItemAdminResponse> create(@RequestBody MenuItemAdminRequest request) {
        return ApiResponse.<MenuItemAdminResponse>builder()
                .result(menuItemService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<MenuItemAdminResponse>> getMenuItemsPagination(@PageableDefault(size = 10, page = 0, sort = "name") Pageable pageable) {

        Page<MenuItemAdminResponse> menuItemAdminResponses = menuItemService.getMenuItemsPagination(pageable);

        return ApiResponse.<Page<MenuItemAdminResponse>>builder()
                .result(menuItemAdminResponses)
                .build();

    }

}
