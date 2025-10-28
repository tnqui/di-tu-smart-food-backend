package com.tranngocqui.ditusmartfoodbackend.dto.admin.order;

import lombok.Builder;

@Builder
public record OrderAdminCreateResponse(
        String orderId,
        String deliveryMethodName,
        String paymentMethodName
) {
}
