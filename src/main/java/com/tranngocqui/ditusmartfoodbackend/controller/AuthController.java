package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LoginRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LogoutRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LogoutResponse;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest logoutRequestRequest) {
        return ResponseEntity.ok(authService.logout(logoutRequestRequest));
    }

}
