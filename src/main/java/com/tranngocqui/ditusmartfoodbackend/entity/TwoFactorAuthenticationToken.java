package com.tranngocqui.ditusmartfoodbackend.entity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TwoFactorAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final int code;

    public TwoFactorAuthenticationToken(String username, String password, int code) {
        super(username, password);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
