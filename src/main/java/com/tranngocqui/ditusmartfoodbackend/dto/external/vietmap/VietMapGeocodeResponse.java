package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record VietMapGeocodeResponse(

        @JsonProperty("ref_id")
        String refId,

        Double distance,
        String address,
        String name,
        String display,

        List<Boundary> boundaries,
        List<String> categories,

        @JsonProperty("entry_points")
        List<Object> entryPoints,

        @JsonProperty("data_old")
        VietMapGeocodeResponse dataOld,

        @JsonProperty("data_new")
        VietMapGeocodeResponse dataNew,

        @JsonProperty("partner_code")
        String partnerCode

) {
    public record Boundary(
            Integer type,
            Long id,
            String name,
            String prefix,

            @JsonProperty("full_name")
            String fullName
    ) {
    }
}