package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

public record TwoFAConfirmRequest(
        String email,
        String code
) {

}
