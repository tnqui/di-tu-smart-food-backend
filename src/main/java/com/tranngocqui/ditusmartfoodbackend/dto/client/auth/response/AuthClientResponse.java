package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record AuthClientResponse(
        UUID userId,
        String email,
        String fullName,
        String phone,
        String avatarUrl,
        List<AddressClientResponse> addresses,
        String accessToken,
        String refreshToken,
        String tokenType
) {
}