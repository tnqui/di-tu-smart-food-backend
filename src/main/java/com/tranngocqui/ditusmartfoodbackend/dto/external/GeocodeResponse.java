package com.tranngocqui.ditusmartfoodbackend.dto.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeocodeResponse {
    private double latitude;
    private double longitude;
    private String matchedAddress;
}
