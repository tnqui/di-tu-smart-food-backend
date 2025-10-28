package com.tranngocqui.ditusmartfoodbackend.service.domain.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.service.BaseService;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryMethodDomainServiceImpl extends BaseService<DeliveryMethod, UUID> implements DeliveryMethodDomainService {
    public DeliveryMethodDomainServiceImpl(JpaRepository<DeliveryMethod, UUID> repository) {
        super(repository);
    }

    @Override
    protected ErrorCode getNotFoundErrorCode() {
        return ErrorCode.DELIVERY_METHOD_NOT_FOUND;
    }

    @Override
    public DeliveryMethod getByIdOrThrow(String id) {
        return findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public DeliveryMethod createDeliveryMethod(DeliveryMethod deliveryMethod) {
        return save(deliveryMethod);
    }

    @Override
    public void deleteDeliveryMethodById(String id) {
        deleteById(UUIDUtils.parseUUUIDFromString(id));
    }

    @Override
    public Page<DeliveryMethod> getDeliveryMethods(Pageable pageable) {
        return findAll(pageable);
    }
}
