package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Builder;

@Builder
public record AddressClientResponse(
        String id,
        String label,
        String fullAddress,
        Double latitude,
        Double longitude,
        boolean defaultAddress
) {
}