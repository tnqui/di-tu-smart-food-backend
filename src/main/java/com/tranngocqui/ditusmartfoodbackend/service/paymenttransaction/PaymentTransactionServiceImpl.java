package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    @Override
    public PaymentTransaction findByTransactionId(UUID transactionId) {
        return null;
    }

    @Override
    public PaymentTransaction save(PaymentTransaction transaction) {
        return null;
    }
}
