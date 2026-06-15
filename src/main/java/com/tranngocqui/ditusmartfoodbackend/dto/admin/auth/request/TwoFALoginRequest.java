package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

public record TwoFALoginRequest(
        String code,
        String tempToken
) {

}
