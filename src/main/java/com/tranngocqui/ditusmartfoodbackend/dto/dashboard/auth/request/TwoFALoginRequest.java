package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import lombok.Data;

@Data
public class TwoFALoginRequest {
    private String code;
    private String tempToken;
}
