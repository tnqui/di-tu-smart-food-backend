package com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymenttransaction.cod;

import com.tranngocqui.ditusmartfoodbackend.dto.external.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymenttransaction.factory.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class CODPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentProvider getProvider() {
        return PaymentProvider.COD;
    }

    @Override
    public PaymentResponse pay(Order order) {
        return PaymentResponse.builder()
                .provider(PaymentProvider.COD)
                .build();
    }
}
