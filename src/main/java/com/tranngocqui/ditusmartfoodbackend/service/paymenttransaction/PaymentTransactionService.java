package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PaymentTransactionService {
    PaymentTransaction findByTransactionId(UUID transactionId);

    PaymentTransaction save(PaymentTransaction transaction);
}
