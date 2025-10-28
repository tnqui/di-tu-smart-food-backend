package com.tranngocqui.ditusmartfoodbackend.service.domain.paymentmethod;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodDomainService {
    PaymentMethod getByIdOrThrow(String id);

    Page<PaymentMethod> getPaymentMethods(Pageable pageable);

    void deletePaymentMethodById(String id);

    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

}
