package com.tranngocqui.ditusmartfoodbackend.dto.auth;


public record Auth2FARequest(
        String email,
        String phone,
        String password,
        String code
) {
}