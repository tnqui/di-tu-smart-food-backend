package com.tranngocqui.ditusmartfoodbackend.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Auth2FAResponse(
        String accessToken,
        String refreshToken,
        String setup2FaToken,
        String confirm2FaToken,
        String verify2FaToken,
        Boolean requires2FA,
        String message,
        Boolean authenticated,
        String qrAuthenticationSetup
) {
}