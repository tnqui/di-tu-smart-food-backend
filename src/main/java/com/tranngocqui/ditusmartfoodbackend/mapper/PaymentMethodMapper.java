package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod.PaymentMethodRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.CreateOrderRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodMapper {
    PaymentMethod toPaymentMethod(PaymentMethodRequest request);
}
