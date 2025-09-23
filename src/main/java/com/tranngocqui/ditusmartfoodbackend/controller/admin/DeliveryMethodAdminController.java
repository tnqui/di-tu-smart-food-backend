package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.deliverymethod.DeliveryMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/delivery-methods")
@RequiredArgsConstructor
public class DeliveryMethodAdminController {
    private final DeliveryMethodService deliveryMethodService;

    @PostMapping
    ApiResponse<DeliveryMethodAdminResponse> create(@RequestBody DeliveryMethodAdminRequest deliveryMethodAdminRequest) {
        return ApiResponse.<DeliveryMethodAdminResponse>builder()
                .result(deliveryMethodService.create(deliveryMethodAdminRequest))
                .build();
    }

    @PatchMapping("{name}")
    ApiResponse<DeliveryMethodAdminResponse> update(@PathVariable Long id, @RequestBody DeliveryMethodAdminRequest deliveryMethodAdminRequest) {
        return ApiResponse.<DeliveryMethodAdminResponse>builder()
                .result(deliveryMethodService.update(id, deliveryMethodAdminRequest))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<DeliveryMethodAdminResponse>> getAll() {
        return ApiResponse.<List<DeliveryMethodAdminResponse>>builder()
                .result(deliveryMethodService.getAll())
                .build();
    }

    @GetMapping()
    ApiResponse<Page<DeliveryMethodAdminResponse>> getPagination(Pageable pageable) {
        return ApiResponse.<Page<DeliveryMethodAdminResponse>>builder()
                .result(deliveryMethodService.getPagination(pageable))
                .build();
    }

    @GetMapping("{name}")
    ApiResponse<DeliveryMethodAdminResponse> get(@PathVariable Long id) {
        return ApiResponse.<DeliveryMethodAdminResponse>builder()
                .result(deliveryMethodService.get(id))
                .build();
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) {
        deliveryMethodService.delete(id);
    }
}
