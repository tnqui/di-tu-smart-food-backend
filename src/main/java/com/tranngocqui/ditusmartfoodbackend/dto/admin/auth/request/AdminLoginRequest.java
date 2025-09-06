package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminLoginRequest {
    @NotBlank(message = "EMAIL_REQUIRED")
    private String email;

    @NotBlank(message = "PASSWORD_REQUIRED")
    private String password;

    @NotNull(message = "TOTP_CODE_REQUIRED")
    private Integer code;
}
