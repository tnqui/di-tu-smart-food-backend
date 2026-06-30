package com.tranngocqui.ditusmartfoodbackend.dto.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Location(
        Double lat,
        Double lng,
        String display,
        String refId,
        Double distance
) {
}
