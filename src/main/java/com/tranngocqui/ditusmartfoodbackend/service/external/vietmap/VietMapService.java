package com.tranngocqui.ditusmartfoodbackend.service.external.vietmap;

import com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap.*;
import com.tranngocqui.ditusmartfoodbackend.enums.vietmap.Vehicle;

import java.util.List;

public interface VietMapService {
    List<VietMapGeocodeResponse> geocoding(String textAddress);

    List<VietMapReverseGeocodingResponse> reverseGeocoding(Double latitude, Double longitude);

    List<VietMapAutoCompleteResponse> autoComplete(String text);

    VietMapPlaceDetailResponse place(String refId);

    VietMapRoutingResponse routing(String latLongStart, String latLongEnd, Vehicle vehicle);

    VietMapMatrixResponse matrix(List<String> points, String sources, String destinations);

}
