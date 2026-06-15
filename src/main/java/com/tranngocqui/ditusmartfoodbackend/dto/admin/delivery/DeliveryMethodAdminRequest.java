package com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DeliveryMethodAdminRequest(
        DeliveryMethodProvider shortName,
        String fullName,
        Integer time,
        BigDecimal price
) {
}