package com.tranngocqui.ditusmartfoodbackend.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auth2FAResponse {
    private String accessToken;
    private String refreshToken;
    private String setup2FaToken;
    private String confirm2FaToken;
    private String verify2FaToken;
    private Boolean requires2FA;
    private String message;
    private Boolean authenticated;
    private String qrAuthenticationSetup;
}
