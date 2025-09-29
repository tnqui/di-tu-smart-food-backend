package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.factory;

import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentServiceFactory {
    private final Map<PaymentProvider, PaymentStrategy> strategies;

    public PaymentServiceFactory(List<PaymentStrategy> paymentStrategies) {
        this.strategies = paymentStrategies
                .stream()
                .collect(Collectors.toMap(PaymentStrategy::getProvider, s -> s));
    }

    public PaymentStrategy getStrategy(PaymentProvider provider) {
        return strategies.get(provider);
    }
}
