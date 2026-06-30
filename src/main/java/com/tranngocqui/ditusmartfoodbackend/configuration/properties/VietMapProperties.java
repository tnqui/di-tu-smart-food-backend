package com.tranngocqui.ditusmartfoodbackend.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "viet-map")
public record VietMapProperties(
        String apiKey,
        String tilemapKey,
        String consoleApplication,
        String apiUrl
) {
}
