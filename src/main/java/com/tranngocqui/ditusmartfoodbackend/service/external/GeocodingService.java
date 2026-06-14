package com.tranngocqui.ditusmartfoodbackend.service.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.tranngocqui.ditusmartfoodbackend.dto.external.GeocodeResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class GeocodingService {

    @Value("${mapbox.token}")
    private String mapboxToken;

    public GeocodeResponse geocode(String fullAddress) {
        try {
            if (fullAddress == null || fullAddress.length() < 10 || !fullAddress.contains(",")) {
                throw new AppException(ErrorCode.ADDRESS_INVALID);
            }

            WebClient client = WebClient.builder()
                    .baseUrl("https://api.mapbox.com")
                    .build();

            JsonNode json = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/geocoding/v5/mapbox.places/{address}.json")
                            .queryParam("access_token", mapboxToken)
                            .queryParam("limit", 1)
                            .queryParam("language", "vi")
                            .build(fullAddress))  // fullAddress sẽ được encode đúng tại đây
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (json == null) {
                throw new AppException(ErrorCode.GEOCODING_SERVICE_ERROR);
            }

            JsonNode features = json.get("features");

            if (features == null || !features.isArray() || features.isEmpty()) {
                throw new IllegalArgumentException("Không tìm thấy địa chỉ phù hợp.");
            }

            JsonNode feature = features.get(0);
            double relevance = feature.get("relevance").asDouble();
            if (relevance < 0.7) {
                throw new IllegalArgumentException("Địa chỉ không đủ rõ ràng.");
            }

            JsonNode center = feature.get("center");
            double lon = center.get(0).asDouble();
            double lat = center.get(1).asDouble();
            String matchedAddress = feature.get("place_name").asText();

            return new GeocodeResponse(lat, lon, matchedAddress);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(ErrorCode.GEOCODING_SERVICE_ERROR);
        }
    }

}
