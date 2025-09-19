package com.tranngocqui.ditusmartfoodbackend.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tempToken;
    private boolean requires2FA;
    private String redirectUrl;
    private String message;
}
