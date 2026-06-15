package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;


public record TwoFAVerifyRequest(
        String preLoginToken,
        String otp
) {

}
