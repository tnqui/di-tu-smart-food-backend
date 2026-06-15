package com.tranngocqui.ditusmartfoodbackend.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tempToken,
        boolean requires2FA,
        String redirectUrl,
        String message
) {
}