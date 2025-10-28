package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.deliverymethod.DeliveryMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}")
    ApiResponse<DeliveryMethodAdminResponse> update(@PathVariable String id, @RequestBody DeliveryMethodAdminRequest deliveryMethodAdminRequest) {
        return ApiResponse.<DeliveryMethodAdminResponse>builder()
                .result(deliveryMethodService.update(id, deliveryMethodAdminRequest))
                .build();
    }

    @GetMapping()
    ApiResponse<Page<DeliveryMethodAdminResponse>> getPagination(Pageable pageable) {
        return ApiResponse.<Page<DeliveryMethodAdminResponse>>builder()
                .result(deliveryMethodService.getPagination(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<DeliveryMethodAdminResponse> get(@PathVariable String id) {
        return ApiResponse.<DeliveryMethodAdminResponse>builder()
                .result(deliveryMethodService.get(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteById(@PathVariable String id) {
        deliveryMethodService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted the delivery method")
                .build();
    }
}
