package com.tranngocqui.ditusmartfoodbackend.dto.external;

public record GeocodeResponse(
        double latitude,
        double longitude,
        String matchedAddress
) {
}
