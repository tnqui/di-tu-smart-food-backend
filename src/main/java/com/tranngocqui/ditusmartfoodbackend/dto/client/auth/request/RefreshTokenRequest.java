package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.request;


import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        String refreshToken
) {
}