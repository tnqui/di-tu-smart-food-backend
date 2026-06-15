package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

public record TwoFASetupRequest(
        String setUpToken,
        String otp
) {

}
