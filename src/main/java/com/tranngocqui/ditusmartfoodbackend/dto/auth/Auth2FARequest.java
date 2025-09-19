package com.tranngocqui.ditusmartfoodbackend.dto.auth;


import lombok.Data;

@Data
public class Auth2FARequest {
    private String email;
    private String phone;
    private String password;
    private String code;
}
