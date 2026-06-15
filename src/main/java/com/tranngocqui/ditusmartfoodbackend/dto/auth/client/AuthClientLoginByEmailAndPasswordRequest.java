package com.tranngocqui.ditusmartfoodbackend.dto.auth.client;

public record AuthClientLoginByEmailAndPasswordRequest(
        String email,
        String password
) {
}
