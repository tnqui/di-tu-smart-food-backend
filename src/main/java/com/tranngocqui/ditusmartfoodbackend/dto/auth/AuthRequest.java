package com.tranngocqui.ditusmartfoodbackend.dto.auth;

public record AuthRequest(
        String identifier,
        String password
) {
}