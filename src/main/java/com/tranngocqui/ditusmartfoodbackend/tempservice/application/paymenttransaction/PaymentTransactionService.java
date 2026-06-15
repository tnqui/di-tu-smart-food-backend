package com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymenttransaction;

import com.tranngocqui.ditusmartfoodbackend.dto.external.payment.PaymentTransactionRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.external.payment.PaymentTransactionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.external.payment.PaymentWebhookResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;

import java.util.UUID;

public interface PaymentTransactionService {
    PaymentTransaction findByTransactionId(UUID transactionId);

    PaymentTransaction save(PaymentTransactionRequest request);

    PaymentTransaction update(PaymentTransaction transaction);

    PaymentTransaction update(PaymentWebhookResponse response);

    PaymentTransactionResponse updateFromCallbackData(PaymentWebhookResponse response);
}
