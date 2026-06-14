package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.service.application.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodAdminController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping()
    public ResponseEntity<ApiResponse<Page<PaymentMethodAdminResponse>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodAdminResponse>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodAdminResponse>> create(@RequestBody PaymentMethodAdminCreateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.create(request)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodAdminResponse>> update(@PathVariable String id, @RequestBody PaymentMethodAdminUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        paymentMethodService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Successfully deleted payment method"));
    }


}
