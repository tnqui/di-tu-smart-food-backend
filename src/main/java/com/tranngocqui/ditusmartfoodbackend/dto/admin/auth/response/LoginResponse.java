package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String refreshToken,
        String message,
        Long expiresIn
) {
}