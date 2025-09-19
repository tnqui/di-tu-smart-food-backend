package com.tranngocqui.ditusmartfoodbackend.controller.auth;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.Auth2FAResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> clientLogin(@RequestBody AuthRequest authRequest) {
        return ApiResponse.<AuthResponse>builder()
                .result(authenticationService.clientLogin(authRequest))
                .build();
    }

    @PostMapping("/dashboard-login")
    public ApiResponse<Auth2FAResponse> dashboardLogin(@RequestBody AuthRequest authRequest) {
        return ApiResponse.<Auth2FAResponse>builder()
                .result(authenticationService.dashboardLogin(authRequest))
                .build();
    }

    @PostMapping("/2fa-setup")
    public ApiResponse<Auth2FAResponse> setup2FA(@RequestBody TwoFASetupRequest twoFASetupRequest) {
        return ApiResponse.<Auth2FAResponse>builder()
                .result(authenticationService.setup2FA(twoFASetupRequest.getSetUpToken()))
                .build();
    }

    @PostMapping("/2fa-confirm")
    public ApiResponse<Auth2FAResponse> confirm2FA(@RequestBody TwoFASetupRequest twoFASetupRequest) {
        return ApiResponse.<Auth2FAResponse>builder()
                .result(authenticationService.confirm2FA(twoFASetupRequest.getSetUpToken(), twoFASetupRequest.getOtp()))
                .build();
    }

    @PostMapping("/verify-2fa")
    public ApiResponse<Auth2FAResponse> verify2FA(@RequestBody @Valid TwoFAVerifyRequest request) {
        return ApiResponse.<Auth2FAResponse>builder()
                .result(authenticationService.verify2FA(request.getPreLoginToken(), request.getOtp()))
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getMe(Authentication authentication) {
        return ApiResponse.<UserResponse>builder()
                .result(authenticationService.getMe(authentication))
                .build();
    }


}
