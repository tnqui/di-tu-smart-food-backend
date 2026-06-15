package com.tranngocqui.ditusmartfoodbackend.dto.auth.client;

import lombok.Builder;

@Builder
public record AuthClientRegisterRequest(
        String email,
        String phone,
        String password,
        String firstName,
        String lastName
) {
}
