package com.tranngocqui.ditusmartfoodbackend.service.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliveryMethodService {
    DeliveryMethodAdminResponse create(DeliveryMethodAdminRequest request);

    DeliveryMethodAdminResponse update(String id, DeliveryMethodAdminRequest request);

    void delete(String id);

    DeliveryMethodAdminResponse get(String id);

    List<DeliveryMethodAdminResponse> getAll();

    Page<DeliveryMethodAdminResponse> getPagination(Pageable pageable);

}
