package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.service.application.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodAdminController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping()
    ApiResponse<Page<PaymentMethodAdminResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<PaymentMethodAdminResponse>>builder()
                .result(paymentMethodService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<PaymentMethodAdminResponse> getById(@PathVariable String id) {
        return ApiResponse.<PaymentMethodAdminResponse>builder()
                .result(paymentMethodService.getById(id))
                .build();
    }

    @PostMapping
    ApiResponse<PaymentMethodAdminResponse> create(@RequestBody PaymentMethodAdminCreateRequest request) {
        return ApiResponse.<PaymentMethodAdminResponse>builder()
                .result(paymentMethodService.create(request))
                .build();
    }

    @PatchMapping("/{id}")
    ApiResponse<PaymentMethodAdminResponse> update(@PathVariable String id, @RequestBody PaymentMethodAdminUpdateRequest request) {
        return ApiResponse.<PaymentMethodAdminResponse>builder()
                .result(paymentMethodService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        paymentMethodService.deleteById(id);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted payment method")
                .build();
    }


}
