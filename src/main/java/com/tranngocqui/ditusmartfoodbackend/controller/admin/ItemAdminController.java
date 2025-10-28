package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.item.ItemAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.service.application.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/items")
@RequiredArgsConstructor
public class ItemAdminController {
    private final ItemService itemService;

    @PostMapping
    public ApiResponse<ItemAdminResponse> create(@RequestBody ItemAdminRequest request) {
        return ApiResponse.<ItemAdminResponse>builder()
                .result(itemService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<ItemAdminResponse>> get(Pageable pageable) {
        return ApiResponse.<Page<ItemAdminResponse>>builder()
                .result(itemService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ItemAdminResponse> getById(@PathVariable String id) {
        return ApiResponse.<ItemAdminResponse>builder()
                .result(itemService.getById(id))
                .build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<ItemAdminResponse> update(@PathVariable String id, @RequestBody ItemAdminUpdateRequest request) {
        return ApiResponse.<ItemAdminResponse>builder()
                .result(itemService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        itemService.deleteById(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted item")
                .build();
    }

}
