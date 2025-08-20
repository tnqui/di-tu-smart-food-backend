package com.tranngocqui.ditusmartfoodbackend.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {
    @NotBlank(message = "EMAIL_OR_PHONE_IS_REQUIRED" )
    private String emailOrPhone;
    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    private String password;
}
