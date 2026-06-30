package com.tranngocqui.ditusmartfoodbackend.dto.client.order;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderConfirmRequest(
        @NotNull(message = "REQUIRED_FIELD_MISSING")
        PaymentMethod paymentMethod,
        @NotNull(message = "REQUIRED_FIELD_MISSING")
        DeliveryMethod deliveryMethod,
        String note,
        @NotNull(message = "REQUIRED_FIELD_MISSING")
        String refId
) {
}
