package com.tranngocqui.ditusmartfoodbackend.service.domain.paymentmethod;

import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentMethodDomainServiceImpl extends BaseService<PaymentMethod, UUID> implements PaymentMethodDomainService {
    public PaymentMethodDomainServiceImpl(JpaRepository<PaymentMethod, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.PAYMENT_METHOD_NOT_SUPPORTED;
    }

    @Override
    public PaymentMethod getByIdOrThrow(String id) {
        return findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<PaymentMethod> getPaymentMethods(Pageable pageable) {
        return findAll(pageable);
    }

    @Override
    public void deletePaymentMethodById(String id) {
        deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethod paymentMethod) {
        return save(paymentMethod);
    }
}
