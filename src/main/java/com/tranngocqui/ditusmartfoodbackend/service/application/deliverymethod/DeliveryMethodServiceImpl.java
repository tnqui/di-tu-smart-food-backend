package com.tranngocqui.ditusmartfoodbackend.service.application.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.mapper.DeliveryMethodMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.DeliveryMethodRepository;
import com.tranngocqui.ditusmartfoodbackend.service.domain.deliverymethod.DeliveryMethodDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    private final DeliveryMethodRepository deliveryMethodRepository;
    private final DeliveryMethodMapper deliveryMethodMapper;
    private final DeliveryMethodDomainService deliveryMethodDomainService;

    @Override
    public DeliveryMethodAdminResponse create(DeliveryMethodAdminRequest request) {
        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodDomainService.createDeliveryMethod(deliveryMethodMapper.toDeliveryMethod(request)));
    }

    @Override
    public DeliveryMethodAdminResponse update(String id, DeliveryMethodAdminRequest request) {
        DeliveryMethod deliveryMethod = deliveryMethodDomainService.getByIdOrThrow(id);

        deliveryMethodMapper.update(request, deliveryMethod);

        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodRepository.save(deliveryMethod));
    }

    @Override
    public void delete(String id) {
        deliveryMethodDomainService.deleteDeliveryMethodById(id);
    }

    @Override
    public DeliveryMethodAdminResponse get(String id) {
        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodDomainService.getByIdOrThrow(id));
    }

    @Override
    public Page<DeliveryMethodAdminResponse> getPagination(Pageable pageable) {
        return deliveryMethodDomainService.getDeliveryMethods(pageable).map(deliveryMethodMapper::toDeliveryMethodAdminResponse);
    }

}
