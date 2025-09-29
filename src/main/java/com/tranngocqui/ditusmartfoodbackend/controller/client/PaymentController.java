package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.factory.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ApiResponse<?> pay(@PathVariable String orderId) throws Exception {
        return ApiResponse.builder()
                .result(paymentService.processPayment(orderId))
                .build();
    }

}