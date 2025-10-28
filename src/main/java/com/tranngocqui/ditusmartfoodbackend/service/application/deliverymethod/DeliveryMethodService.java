package com.tranngocqui.ditusmartfoodbackend.service.application.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryMethodService {
    DeliveryMethodAdminResponse create(DeliveryMethodAdminRequest request);

    DeliveryMethodAdminResponse update(String id, DeliveryMethodAdminRequest request);

    void delete(String id);

    DeliveryMethodAdminResponse get(String id);

    Page<DeliveryMethodAdminResponse> getPagination(Pageable pageable);
}
