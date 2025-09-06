package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Data;

@Data
public class TwoFALoginRequest {
    private String code;
    private String tempToken;
}
