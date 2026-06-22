//package com.tranngocqui.ditusmartfoodbackend.tempservice.deliverymethod;
//
//import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
//import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
//import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
//import com.tranngocqui.ditusmartfoodbackend.repository.DeliveryMethodRepository;
//import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class DeliveryMethodDomainServiceImpl implements DeliveryMethodDomainService {
//    private final BaseService<DeliveryMethod, UUID> baseService;
//    private final DeliveryMethodRepository deliveryMethodRepository;
//
//    public DeliveryMethodDomainServiceImpl(BaseServiceFactory factory, DeliveryMethodRepository deliveryMethodRepository) {
//        this.baseService = factory.create(deliveryMethodRepository);
//        this.deliveryMethodRepository = deliveryMethodRepository;
//    }
//
//    @Override
//    public DeliveryMethod getByIdOrThrow(String id) {
//        return baseService.findByIdOrThrow(UUIDUtils.parseUUUIDFromString(id));
//    }
//
//    @Override
//    public DeliveryMethod getByCodeOrThrow(DeliveryMethodProvider code) {
//        return deliveryMethodRepository.findOptionalByCode(code).orElseThrow(() -> new AppException(baseService.getNotFoundErrorCode()));
//    }
//
//    @Override
//    public DeliveryMethod createDeliveryMethod(DeliveryMethod deliveryMethod) {
//        return baseService.save(deliveryMethod);
//    }
//
//    @Override
//    public void deleteDeliveryMethodById(String id) {
//        baseService.deleteById(UUIDUtils.parseUUUIDFromString(id));
//    }
//
//    @Override
//    public Page<DeliveryMethod> getDeliveryMethods(Pageable pageable) {
//        return baseService.findAll(pageable);
//    }
//}
