package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Data;

@Data
public class TwoFASetupRequest {
    private String setUpToken;
    private String otp;
}
