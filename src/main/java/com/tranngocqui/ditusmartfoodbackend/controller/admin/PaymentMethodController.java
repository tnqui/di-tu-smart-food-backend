package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.PaymentMethodResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.service.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @PostMapping
    ApiResponse<PaymentMethodResponse> save(@RequestBody PaymentMethodRequest request) {
        return ApiResponse.<PaymentMethodResponse>builder()
                .result(paymentMethodService.create(request))
                .build();

    }
}
