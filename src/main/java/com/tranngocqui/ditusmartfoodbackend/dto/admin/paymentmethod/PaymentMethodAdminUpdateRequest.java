package com.tranngocqui.ditusmartfoodbackend.dto.admin.paymentmethod;

public record PaymentMethodAdminUpdateRequest (
        String id,
        String name,
        String description
){
}
