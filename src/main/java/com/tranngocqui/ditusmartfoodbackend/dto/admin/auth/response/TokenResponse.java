package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String qrAuthenticationSetup;
    private String tempToken;
    private boolean requires2FA;
    private String redirectUrl;
    private String message;
    private boolean authenticated;
}
