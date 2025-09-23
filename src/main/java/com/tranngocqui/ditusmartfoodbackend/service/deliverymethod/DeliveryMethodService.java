package com.tranngocqui.ditusmartfoodbackend.service.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliveryMethodService {
    DeliveryMethodAdminResponse create(DeliveryMethodAdminRequest request);

    DeliveryMethodAdminResponse update(Long id, DeliveryMethodAdminRequest request);

    void delete(Long id);

    DeliveryMethodAdminResponse get(Long id);

    List<DeliveryMethodAdminResponse> getAll();

    Page<DeliveryMethodAdminResponse> getPagination(Pageable pageable);

    DeliveryMethod findById(Long id);
}
