package com.tranngocqui.ditusmartfoodbackend.controller.external;


import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.paymenttransaction.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment/momo")
@RequiredArgsConstructor
public class MoMoController {
    private final PaymentTransactionService paymentTransactionService;

    @PostMapping
    public ApiResponse<PaymentTransactionResponse> callbackWebhook(@RequestBody PaymentWebhookResponse response) {
        return ApiResponse.<PaymentTransactionResponse>builder()
                .result(paymentTransactionService.updateFromCallbackData(response))
                .build();
    }

}
