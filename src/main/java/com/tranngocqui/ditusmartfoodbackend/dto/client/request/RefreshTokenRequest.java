package com.tranngocqui.ditusmartfoodbackend.dto.client.request;


import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        String refreshToken
) {
}