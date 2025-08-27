package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {
    @NotBlank(message = "PHONE_IS_REQUIRED")
    private String email;
    @NotBlank(message = "EMAIL_IS_REQUIRED")
    private String phone;

    private Integer TwoFactorCode;

    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    private String password;
}
