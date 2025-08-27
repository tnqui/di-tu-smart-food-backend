package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.TokenResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.RegisterResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;

import java.text.ParseException;

public interface AuthenticationService {
    TokenResponse token(TokenRequest loginRequest);

    boolean authenticate(LogoutRequest logoutRequest);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    RegisterResponse register(RegisterRequest registerRequest);

    String generateTwoFactorSecret(User user);

    void enableTwoFactor(User user);

    void disableTwoFactor(User user);

    boolean verifyTwoFactorCode(String secret, int code);

    boolean validatePassword(String rawPassword, String encodedPassword);
}
