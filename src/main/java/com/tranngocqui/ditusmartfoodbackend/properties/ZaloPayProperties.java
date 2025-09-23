package com.tranngocqui.ditusmartfoodbackend.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zalo")
public record ZaloPayProperties(
        String appId,
        String key1,
        String callbackUrl,
        String endpoint
) {
}
