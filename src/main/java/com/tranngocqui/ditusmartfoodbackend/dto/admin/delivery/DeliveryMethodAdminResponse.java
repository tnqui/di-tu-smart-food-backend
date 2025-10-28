package com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery;

import java.math.BigDecimal;

public record DeliveryMethodAdminResponse(
        String id,
        String shortName,
        String fullName,
        BigDecimal pricePerKm) {
}
