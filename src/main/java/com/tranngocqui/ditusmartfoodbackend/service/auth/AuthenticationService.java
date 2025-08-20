package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.IntrospectRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LogoutRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.AuthenticationResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.RegisterResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse token(TokenRequest loginRequest);
    boolean authenticate(LogoutRequest logoutRequest);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    RegisterResponse register(RegisterRequest registerRequest);
}
