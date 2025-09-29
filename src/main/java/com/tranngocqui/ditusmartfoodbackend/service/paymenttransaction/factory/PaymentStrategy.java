package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.factory;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;

public interface PaymentStrategy {
    PaymentProvider getProvider();

    PaymentResponse pay(Order order) throws Exception;
}
