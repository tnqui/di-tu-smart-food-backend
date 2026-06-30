package com.tranngocqui.ditusmartfoodbackend.service.external.vietmap;

import com.tranngocqui.ditusmartfoodbackend.configuration.properties.VietMapProperties;
import com.tranngocqui.ditusmartfoodbackend.constant.VietMapPrefixUrl;
import com.tranngocqui.ditusmartfoodbackend.dto.external.vietmap.*;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.VietMapDisplayType;
import com.tranngocqui.ditusmartfoodbackend.enums.vietmap.Vehicle;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VietMapServiceImpl implements VietMapService {
    private final RestClient restClient;
    private final VietMapProperties vietMapProperties;

    private URI buildUri(String endpoint, Map<String, Object> params) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(vietMapProperties.apiUrl() + endpoint)
                .queryParam("apikey", vietMapProperties.apiKey());

        params.forEach((key, value) -> {
            if (value instanceof List<?>) {
                ((List<?>) value).forEach(v -> builder.queryParam(key, v));
            } else {
                builder.queryParam(key, value);
            }
        });

        return builder.buildAndExpand().encode().toUri();
    }

    private <T> T callVietMapApi(String endpoint, Map<String, Object> params,
                                 ParameterizedTypeReference<T> responseType) {
        URI uri = buildUri(endpoint, params);

        return restClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new AppException(ErrorCode.VIET_MAP_ERROR);
                })
                .body(responseType);
    }

    private Map<String, Object> createParams(Object... keysAndValues) {
        Map<String, Object> params = new LinkedHashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            if (i + 1 < keysAndValues.length) {
                params.put((String) keysAndValues[i], keysAndValues[i + 1]);
            }
        }
        return params;
    }


    @Override
    public List<VietMapGeocodeResponse> geocoding(String textAddress) {
        Map<String, Object> params = createParams("text", textAddress);

        return callVietMapApi(
                VietMapPrefixUrl.GEOCODING_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public List<VietMapReverseGeocodingResponse> reverseGeocoding(Double latitude, Double longitude) {
        Map<String, Object> params = createParams(
                "lat", latitude,
                "lng", longitude,
                "display_type", VietMapDisplayType.NEW_OLD_FORMAT.getTypeNumber()
        );

        return callVietMapApi(
                VietMapPrefixUrl.REVERSE_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public List<VietMapAutoCompleteResponse> autoComplete(String text) {
        Map<String, Object> params = createParams(
                "text", text,
                "display_type", VietMapDisplayType.NEW_OLD_FORMAT.getTypeNumber()
        );

        return callVietMapApi(
                VietMapPrefixUrl.AUTOCOMPLETE_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public VietMapPlaceDetailResponse place(String refId) {
        Map<String, Object> params = createParams("refid", refId);

        return callVietMapApi(
                VietMapPrefixUrl.PLACE_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public VietMapRoutingResponse routing(String latLongStart, String latLongEnd, Vehicle vehicle) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("point", List.of(latLongStart, latLongEnd));
        params.put("vehicle", vehicle.name().toLowerCase());

        return callVietMapApi(
                VietMapPrefixUrl.ROUTE_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    @Override
    public VietMapMatrixResponse matrix(List<String> points, String sources, String destinations) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("point", points);
        params.put("sources", sources);
        params.put("destinations", destinations);

        return callVietMapApi(
                VietMapPrefixUrl.MATRIX_URL,
                params,
                new ParameterizedTypeReference<>() {
                }
        );
    }
}