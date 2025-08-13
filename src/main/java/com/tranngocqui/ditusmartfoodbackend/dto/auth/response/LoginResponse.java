package com.tranngocqui.ditusmartfoodbackend.dto.auth.response;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoginResponse {
    private Long id;
    private String token;
    private Set<String> roles;
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
