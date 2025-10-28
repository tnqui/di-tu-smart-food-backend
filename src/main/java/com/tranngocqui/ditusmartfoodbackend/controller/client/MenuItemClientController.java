package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.service.application.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemClientController {
    private final ItemService itemService;
//
//    @GetMapping
//    public ApiResponse<Page<MenuItemClientResponse>> getMenuItemsByCategoryId(
//            @RequestParam(required = false) Long categoryId,
//            @RequestParam(required = false) Integer page,
//            @RequestParam(defaultValue = "10") Integer size) {
//
//        int safePage = (page == null || page < 1) ? 1 : page;
//        Pageable pageable = PageRequest.of(safePage - 1, size);
//
//        Page<MenuItemClientResponse> menuItems = itemService.getMenuItemPaginationByCategoryId(pageable, categoryId);
//
//        return ApiResponse.<Page<MenuItemClientResponse>>builder()
//                .result(menuItems)
//                .build();
//    }
}
