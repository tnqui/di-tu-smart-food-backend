package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {

    private String email;

    private String phone;

    private Integer code;

    private String password;
}
