package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VietMapReverseGeocodingResponse(
        Double lat,
        Double lng,

        @JsonProperty("ref_id")
        String refId,

        Double distance,
        String address,
        String name,
        String display,

        List<Boundaries> boundaries,
        List<Object> categories,

        @JsonProperty("entry_points")
        List<Object> entryPoints,

        @JsonProperty("data_old")
        VietMapReverseGeocodingResponse dataOld,

        @JsonProperty("data_new")
        VietMapReverseGeocodingResponse dataNew

) {
    public record Boundaries(
            Integer type,
            Integer id,
            String name,
            String prefix,

            @JsonProperty("full_name")
            String fullName
    ) {
    }
}