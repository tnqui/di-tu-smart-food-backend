package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.paymentmethod;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethodProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentMethodDomainService {
    PaymentMethod getByIdOrThrow(String id);

    Page<PaymentMethod> getPaymentMethods(Pageable pageable);

    PaymentMethod getByCodeOrThrow(PaymentMethodProvider name);

    void deletePaymentMethodById(String id);

    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);

}
