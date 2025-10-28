package com.tranngocqui.ditusmartfoodbackend.service.domain.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryMethodDomainService {
    DeliveryMethod getByIdOrThrow(String id);

    DeliveryMethod createDeliveryMethod(DeliveryMethod deliveryMethod);

    void deleteDeliveryMethodById(String id);

    Page<DeliveryMethod> getDeliveryMethods(Pageable pageable);
}
