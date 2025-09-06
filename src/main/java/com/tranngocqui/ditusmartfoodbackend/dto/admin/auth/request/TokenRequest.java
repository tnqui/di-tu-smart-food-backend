package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.*;

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
