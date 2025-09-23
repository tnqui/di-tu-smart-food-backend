package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.service.payment.PaymentService;
import com.tranngocqui.ditusmartfoodbackend.service.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


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