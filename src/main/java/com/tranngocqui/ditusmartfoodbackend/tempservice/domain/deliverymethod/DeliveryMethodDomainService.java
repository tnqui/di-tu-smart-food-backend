package com.tranngocqui.ditusmartfoodbackend.tempservice.domain.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryMethodDomainService {
    DeliveryMethod getByIdOrThrow(String id);

    DeliveryMethod getByCodeOrThrow(DeliveryMethodProvider code);

    DeliveryMethod createDeliveryMethod(DeliveryMethod deliveryMethod);

    void deleteDeliveryMethodById(String id);

    Page<DeliveryMethod> getDeliveryMethods(Pageable pageable);
}
