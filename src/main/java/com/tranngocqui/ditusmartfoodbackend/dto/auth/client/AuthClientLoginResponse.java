package com.tranngocqui.ditusmartfoodbackend.dto.auth.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthClientLoginResponse(
        String accessToken,
        String refreshToken
) {
}
