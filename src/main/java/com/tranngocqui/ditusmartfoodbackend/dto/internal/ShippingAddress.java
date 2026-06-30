package com.tranngocqui.ditusmartfoodbackend.dto.internal;

import lombok.Builder;

@Builder
public record ShippingAddress(
        String rawAddress,
        String matchAddress,
        String refId,
        Double lng,
        Double lat
) {
}
