package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record VietMapRoutingResponse(
        String license,
        String code,
        String messages,
        List<Path> paths
) {
    public record Path(
            Double distance,
            Double weight,
            Long time,
            Integer transfers,

            @JsonProperty("points_encoded")
            Boolean pointsEncoded,

            List<Double> bbox,
            String points,

            List<Instruction> instructions,

            @JsonProperty("snapped_waypoints")
            String snappedWaypoints
    ) {
    }

    public record Instruction(
            Double distance,
            Double heading,
            Integer sign,
            List<Integer> interval,
            String text,
            Long time,

            @JsonProperty("street_name")
            String streetName,

            @JsonProperty("last_heading")
            Double lastHeading
    ) {
    }
}