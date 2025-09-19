package com.tranngocqui.ditusmartfoodbackend.service.deliverymethod;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.DeliveryMethodMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.DeliveryMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    private final DeliveryMethodRepository deliveryMethodRepository;
    private final DeliveryMethodMapper deliveryMethodMapper;

    @Override
    public DeliveryMethodAdminResponse create(DeliveryMethodAdminRequest request) {
        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodRepository.save(deliveryMethodMapper.toDeliveryMethod(request)));
    }

    @Override
    public DeliveryMethodAdminResponse update(String name, DeliveryMethodAdminRequest request) {
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND));

        deliveryMethodMapper.update(request, deliveryMethod);

        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodRepository.save(deliveryMethod));
    }

    @Override
    public void delete(String id) {
        deliveryMethodRepository.deleteById(id);
    }

    @Override
    public DeliveryMethodAdminResponse get(String id) {
        return deliveryMethodMapper.toDeliveryMethodAdminResponse(deliveryMethodRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND)));
    }

    @Override
    public List<DeliveryMethodAdminResponse> getAll() {
        return deliveryMethodMapper.toDeliveryMethodAdminResponseList(deliveryMethodRepository.findAll());
    }

    @Override
    public Page<DeliveryMethodAdminResponse> getPagination(Pageable pageable) {
        return deliveryMethodMapper.toDeliveryMethodAdminResponsePage(deliveryMethodRepository.findAll(pageable));
    }
}
