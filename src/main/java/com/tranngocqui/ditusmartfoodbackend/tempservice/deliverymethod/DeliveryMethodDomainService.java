package com.tranngocqui.ditusmartfoodbackend.tempservice.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryMethodDomainService {
    DeliveryMethod getByIdOrThrow(String id);

    DeliveryMethod getByCodeOrThrow(DeliveryMethod code);

    DeliveryMethod createDeliveryMethod(DeliveryMethod deliveryMethod);

    void deleteDeliveryMethodById(String id);

    Page<DeliveryMethod> getDeliveryMethods(Pageable pageable);
}
