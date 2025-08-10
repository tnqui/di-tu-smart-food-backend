package com.tranngocqui.ditusmartfoodbackend.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateResponse {
    private Long id;
    private String email;
    private String phone;
}

