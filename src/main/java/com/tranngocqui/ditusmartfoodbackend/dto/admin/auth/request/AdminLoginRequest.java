package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminLoginRequest(

        @NotBlank(message = "EMAIL_REQUIRED")
        String email,

        @NotBlank(message = "PASSWORD_REQUIRED")
        String password,

        @NotNull(message = "TOTP_CODE_REQUIRED")
        Integer code

) {
}