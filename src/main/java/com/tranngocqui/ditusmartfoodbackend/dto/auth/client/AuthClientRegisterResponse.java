package com.tranngocqui.ditusmartfoodbackend.dto.auth.client;

import lombok.Builder;

@Builder
public record AuthClientRegisterResponse(
        String email
) {
}
