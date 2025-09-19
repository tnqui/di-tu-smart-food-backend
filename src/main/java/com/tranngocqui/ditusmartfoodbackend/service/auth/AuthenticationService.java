package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.TokenResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.RegisterResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.Auth2FAResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.security.core.Authentication;

import java.text.ParseException;

public interface AuthenticationService {
    /**
     * Đăng nhập bước 1 - xác thực email/password
     */
    TokenResponse token(TokenRequest request);

    /**
     * Đăng nhập bước 2 - xác thực 2FA (nếu cần)
     */
    TokenResponse verify2FA(TwoFALoginRequest request);


    /**
     * Đăng xuất
     */
    boolean authenticate();

    //refactor
    AuthResponse clientLogin(AuthRequest authRequest);

    Auth2FAResponse dashboardLogin(AuthRequest authRequest);

    Auth2FAResponse setup2FA(String setup2FaToken);

    Auth2FAResponse confirm2FA(String twoFactorSetupToken, String code);
    Auth2FAResponse verify2FA(String preLoginToken, String otp);
    UserResponse getMe(Authentication authentication);

    //reactor


    /**
     * Kiểm tra token có hợp lệ không
     */
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    /**
     * Đăng ký tài khoản mới
     */
    RegisterResponse register(RegisterRequest registerRequest);

    /**
     * Tạo secret key cho 2FA
     */
    String generateTwoFactorSecret(User user);

    /**
     * Bật 2FA
     */
    void enableTwoFactor(User user);

    /**
     * Tắt 2FA
     */
    void disableTwoFactor(User user);

    /**
     * Xác thực mã 2FA
     */
    boolean verifyTwoFactorCode(String secret, int code);

    /**
     * Xác thực password
     */
    boolean validatePassword(String rawPassword, String encodedPassword);


}
