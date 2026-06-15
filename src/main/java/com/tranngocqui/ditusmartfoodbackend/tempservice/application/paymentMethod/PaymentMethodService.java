package com.tranngocqui.ditusmartfoodbackend.tempservice.application.paymentMethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentMethodService {
    Page<PaymentMethodAdminResponse> getAll(Pageable pageable);

    PaymentMethodAdminResponse getById(String id);

    PaymentMethodAdminResponse create(PaymentMethodAdminCreateRequest request);

    PaymentMethodAdminResponse update(String id, PaymentMethodAdminUpdateRequest request);

    void deleteById(String id);
}
