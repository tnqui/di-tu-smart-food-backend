package com.tranngocqui.ditusmartfoodbackend.dto.auth.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthClientRegisterRequest(
        @Email(message = "INVALID_EMAIL_FORMAT") String email,
        @NotBlank(message = "INVALID_PHONE_FORMAT") String phone,
        @NotBlank(message = "INVALID_PASSWORD_FORMAT") String password,
        @NotBlank(message = "INVALID_INPUT_FORMAT") String firstName,
        @NotBlank(message = "INVALID_INPUT_FORMAT") String lastName
) {
}
