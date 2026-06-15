package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.paymentmethod;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethodProvider;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.PaymentMethodRepository;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseService;
import com.tranngocqui.ditusmartfoodbackend.tempservice.BaseServiceFactory;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentMethodDomainServiceImpl implements PaymentMethodDomainService {

    private final BaseService<PaymentMethod, UUID> baseService;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodDomainServiceImpl(BaseServiceFactory factory, PaymentMethodRepository PaymentMethodRepository, PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.baseService = factory.create(PaymentMethodRepository);
    }

    @Override
    public PaymentMethod getByIdOrThrow(String id) {
        return baseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<PaymentMethod> getPaymentMethods(Pageable pageable) {
        return baseService.findAll(pageable);
    }

    @Override
    public PaymentMethod getByCodeOrThrow(PaymentMethodProvider code) {
        return paymentMethodRepository.findOptionalByCode(code).orElseThrow(() -> new AppException(ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED));
    }

    @Override
    public void deletePaymentMethodById(String id) {
        baseService.deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return baseService.save(paymentMethod);
    }
}
