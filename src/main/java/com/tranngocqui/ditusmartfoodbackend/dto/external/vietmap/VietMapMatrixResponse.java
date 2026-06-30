package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import java.util.List;

public record VietMapMatrixResponse(
        String code,
        String messages,
        List<List<Double>> durations,
        List<List<Double>> distances
) {
}