package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.request;

public record AuthClientRequest(
        String identifier,
        String password
) {
}

