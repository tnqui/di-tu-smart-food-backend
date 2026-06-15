package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TokenResponse(
        String accessToken,
        String refreshToken,
        String qrAuthenticationSetup,
        String tempToken,
        boolean requires2FA,
        String redirectUrl,
        String message,
        boolean authenticated
) {
}