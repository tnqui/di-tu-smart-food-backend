package com.tranngocqui.ditusmartfoodbackend.service.paymentMethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.PaymentMethodResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;

import java.util.UUID;

public interface PaymentMethodService {
    PaymentMethod findById(Long id);

    PaymentMethodResponse create(PaymentMethodRequest request);

}
