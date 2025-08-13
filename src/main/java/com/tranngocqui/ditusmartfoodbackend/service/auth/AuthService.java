package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LoginRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LogoutRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LogoutResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LogoutResponse logout(LogoutRequest logoutRequest);
}
