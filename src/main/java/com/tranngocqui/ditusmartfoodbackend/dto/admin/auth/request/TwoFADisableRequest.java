package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

public record TwoFADisableRequest(
         String email,
         String code
) {

}
