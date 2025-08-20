package com.tranngocqui.ditusmartfoodbackend.dto.auth.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "PHONE_OR_EMAIL_IS_REQUIRED")
    @Pattern(regexp = "^(?:[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}|[0-9+\\-\\s()]{10,15})$",
            message = "PHONE_OR_EMAIL_INVALID")
    private String emailOrPhone;

    @NotBlank(message = "PASSWORD_IS_REQUIRED")
    @Size(min = 8, max = 20, message = "PASSWORD_BETWEEN_8_TO_20_CHARACTERS")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "PASSWORD_TOO_WEAK"
    )
    private String password;

    @NotBlank(message = "PASSWORD_CONFIRMED")
    private String confirmPassword;

    @AssertTrue(message = "PASSWORD_DOES_NOT_MATCH")
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }

}
