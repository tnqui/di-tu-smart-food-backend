package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.DeliveryMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryMethodMapper {
    DeliveryMethodAdminResponse toDeliveryMethodAdminResponse(DeliveryMethod deliveryMethod);

    void update(DeliveryMethodAdminRequest request, @MappingTarget DeliveryMethod deliveryMethod);

    DeliveryMethod toDeliveryMethod(DeliveryMethodAdminRequest request);

    List<DeliveryMethodAdminResponse> toDeliveryMethodAdminResponseList(List<DeliveryMethod> deliveryMethods);

    default Page<DeliveryMethodAdminResponse> toDeliveryMethodAdminResponsePage(Page<DeliveryMethod> deliveryMethods) {
        return deliveryMethods.map(this::toDeliveryMethodAdminResponse);
    }

    ;
}
