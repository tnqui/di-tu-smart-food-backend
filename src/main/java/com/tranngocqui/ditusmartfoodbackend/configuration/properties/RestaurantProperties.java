package com.tranngocqui.ditusmartfoodbackend.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "restaurant")
public record RestaurantProperties(
        Double lat,
        Double lng
) {
}
