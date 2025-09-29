package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PaymentTransactionService {
    PaymentTransaction findByTransactionId(UUID transactionId);

    PaymentTransaction save(PaymentTransactionRequest request);

    PaymentTransaction update(PaymentTransaction transaction);

    PaymentTransaction update(PaymentWebhookResponse response);

    PaymentTransactionResponse updateFromCallbackData(PaymentWebhookResponse response);
}
