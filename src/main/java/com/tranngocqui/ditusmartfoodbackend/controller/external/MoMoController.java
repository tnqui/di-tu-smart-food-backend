package com.tranngocqui.ditusmartfoodbackend.controller.external;


import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookBase;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import com.tranngocqui.ditusmartfoodbackend.mapper.PaymentTransactionMapper;
import com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.PaymentTransactionService;
import jakarta.transaction.Transactional;
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
