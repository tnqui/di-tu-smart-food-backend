package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}