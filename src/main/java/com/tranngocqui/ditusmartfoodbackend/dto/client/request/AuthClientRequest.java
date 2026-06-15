package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

public record AuthClientRequest(
        String identifier,
        String password
) {
}

