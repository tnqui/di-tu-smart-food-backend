package com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VietMapPlaceDetailResponse(
        String display,
        String name,

        @JsonProperty("hs_num")
        String hsNum,

        String street,
        String address,

        @JsonProperty("city_id")
        Integer cityId,
        String city,

        @JsonProperty("district_id")
        Integer districtId,
        String district,

        @JsonProperty("ward_id")
        Integer wardId,
        String ward,

        Double lat,
        Double lng
) {
}