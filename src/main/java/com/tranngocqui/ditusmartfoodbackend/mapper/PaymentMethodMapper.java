package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMethodMapper {
    PaymentMethodAdminResponse toPaymentMethodAdminResponse(PaymentMethod paymentMethod);

    PaymentMethod toPaymentMethod(PaymentMethodAdminCreateRequest request);

    void update(PaymentMethodAdminUpdateRequest request, @MappingTarget PaymentMethod paymentMethod);

}
