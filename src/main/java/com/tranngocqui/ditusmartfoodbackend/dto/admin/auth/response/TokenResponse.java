package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {
    private String token;
    private String tempToken;
    private boolean requires2FA;
    private String redirectUrl;
    private String message;
    private boolean authenticated;
}
