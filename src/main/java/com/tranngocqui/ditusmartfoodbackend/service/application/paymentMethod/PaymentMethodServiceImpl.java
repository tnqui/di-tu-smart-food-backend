package com.tranngocqui.ditusmartfoodbackend.service.application.paymentMethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.mapper.PaymentMethodMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentMethodRepository;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.service.domain.paymentmethod.PaymentMethodDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodDomainService paymentMethodDomainService;

    @Override
    public Page<PaymentMethodAdminResponse> getAll(Pageable pageable) {
        return paymentMethodDomainService.getPaymentMethods(pageable).map(paymentMethodMapper::toPaymentMethodAdminResponse);
    }

    @Override
    public PaymentMethodAdminResponse getById(String id) {
        return paymentMethodMapper.toPaymentMethodAdminResponse(paymentMethodDomainService.getByIdOrThrow(id));
    }

    @Override
    public PaymentMethodAdminResponse create(PaymentMethodAdminCreateRequest request) {
        return paymentMethodMapper.toPaymentMethodAdminResponse(paymentMethodDomainService.createPaymentMethod(paymentMethodMapper.toPaymentMethod(request)));
    }

    @Override
    public PaymentMethodAdminResponse update(String id, PaymentMethodAdminUpdateRequest request) {
        PaymentMethod paymentMethod = paymentMethodDomainService.getByIdOrThrow(id);

        paymentMethodMapper.update(request, paymentMethod);

        return paymentMethodMapper.toPaymentMethodAdminResponse(paymentMethodDomainService.createPaymentMethod(paymentMethod));
    }

    @Override
    public void deleteById(String id) {
        paymentMethodDomainService.deletePaymentMethodById(id);
    }
}
