package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Data;

@Data
public class TwoFAVerifyRequest {
    private String preLoginToken;
    private String otp;
}
