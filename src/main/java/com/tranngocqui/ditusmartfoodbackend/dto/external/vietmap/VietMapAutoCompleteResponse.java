package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VietMapAutoCompleteResponse(

        @JsonProperty("ref_id")
        String refId,
        
        Double distance,
        String address,
        String name,
        String display,

        List<Boundary> boundaries,
        List<Object> categories,

        @JsonProperty("entry_points")
        List<Object> entryPoints

) {
    public record Boundary(
            Integer type,
            Integer id,
            String name,
            String prefix,

            @JsonProperty("full_name")
            String fullName
    ) {
    }
}