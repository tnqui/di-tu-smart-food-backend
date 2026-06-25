package com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DeliveryMethodAdminRequest(
        DeliveryMethod shortName,
        String fullName,
        Integer time,
        BigDecimal price
) {
}