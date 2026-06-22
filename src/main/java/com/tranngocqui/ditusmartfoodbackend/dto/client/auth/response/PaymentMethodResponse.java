package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

@Builder
public record PaymentMethodResponse(
        String id,
        String name,
        String description
) {
}