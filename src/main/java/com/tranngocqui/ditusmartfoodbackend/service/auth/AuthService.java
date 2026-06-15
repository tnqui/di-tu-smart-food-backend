package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginByEmailAndPasswordRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientRegisterRequest;

import java.util.Map;

public interface AuthService {
    void clientRegister(AuthClientRegisterRequest request);

    void verifyEmailVerificationLink(String token);

    AuthClientLoginResponse clientLoginByEmailAndPassword(AuthClientLoginByEmailAndPasswordRequest request);

    Map<String, String> refresh(String token);

}
