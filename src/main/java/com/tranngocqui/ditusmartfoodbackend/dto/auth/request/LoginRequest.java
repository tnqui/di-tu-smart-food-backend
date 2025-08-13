package com.tranngocqui.ditusmartfoodbackend.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Tên đăng nhập (Email hoặc Số điện thoại) là bắt buộc" )
    private String username;
    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;
}
