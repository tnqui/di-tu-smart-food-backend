package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.deliverymethod.DeliveryMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/delivery-methods")
@RequiredArgsConstructor
public class DeliveryMethodAdminController {
    private final DeliveryMethodService deliveryMethodService;

    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryMethodAdminResponse>> create(@RequestBody DeliveryMethodAdminRequest deliveryMethodAdminRequest) {
        return ResponseEntity.ok(ApiResponse.success(deliveryMethodService.create(deliveryMethodAdminRequest)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryMethodAdminResponse>> update(@PathVariable String id, @RequestBody DeliveryMethodAdminRequest deliveryMethodAdminRequest) {
        return ResponseEntity.ok(ApiResponse.success(deliveryMethodService.update(id, deliveryMethodAdminRequest)));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<DeliveryMethodAdminResponse>>> getPagination(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(deliveryMethodService.getPagination(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryMethodAdminResponse>> get(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(deliveryMethodService.get(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable String id) {
        deliveryMethodService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted the delivery method"));
    }
}
