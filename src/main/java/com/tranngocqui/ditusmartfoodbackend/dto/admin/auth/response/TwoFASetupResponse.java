package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import lombok.Builder;

@Builder
public record TwoFASetupResponse(
        String qrAuthenticationSetup,
        String message
) {
}