package com.tranngocqui.ditusmartfoodbackend.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.*;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthenticationService;
import com.tranngocqui.ditusmartfoodbackend.service.auth.GoogleAuthenticatorService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("admin/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final GoogleAuthenticatorService googleAuthenticatorService;


    /**
     * Bước 1: Đăng nhập với email/password
     * - Nếu không có 2FA: trả về access token
     * - Nếu có 2FA: trả về temp token và yêu cầu nhập mã 2FA
     */
    @PostMapping("/login")
    public ApiResponse<TokenResponse> authenticate(@RequestBody @Valid TokenRequest request) {
        TokenResponse result = authenticationService.token(request);
        return ApiResponse.<TokenResponse>builder()
                .result(result)
                .build();
    }

    /**
     * Bước 2: Xác thực 2FA và nhận access token
     */
    @PostMapping("/verify-2fa")
    public ApiResponse<TokenResponse> verify2FA(@RequestBody @Valid TwoFALoginRequest request) {
        TokenResponse result = authenticationService.verify2FA(request);
        return ApiResponse.<TokenResponse>builder()
                .result(result)
                .build();
    }

    /**
     * Kiểm tra token có hợp lệ không
     */
    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    /**
     * Đăng ký tài khoản mới
     */
    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        var result = authenticationService.register(request);
        return ApiResponse.<RegisterResponse>builder()
                .result(result)
                .build();
    }

    /**
     * Đăng xuất
     */
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) {
        authenticationService.authenticate(request);
        return ApiResponse.<Void>builder()
                .message("Đăng xuất thành công")
                .build();
    }

    /**
     * Thiết lập 2FA - Bước 1: Tạo secret key và QR code
     */
    @PostMapping("/2fa/setup")
    public ApiResponse<TwoFASetupResponse> setup2FA(@RequestBody TwoFASetupRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (Boolean.TRUE.equals(user.getTwoFactorEnabled())) {
            return ApiResponse.<TwoFASetupResponse>builder()
                    .message("2FA đã được bật cho tài khoản này")
                    .build();
        }

        String secret = authenticationService.generateTwoFactorSecret(user);

        String qrCodeUrl = googleAuthenticatorService.generateQRCodeUrl(user.getEmail(), secret,"Di Tu Smart Food");

        TwoFASetupResponse response = TwoFASetupResponse.builder()
                .qrAuthenticationSetup(qrCodeUrl)
                .message("Vui lòng quét mã QR và nhập mã xác thực để hoàn tất thiết lập 2FA")
                .build();

        return ApiResponse.<TwoFASetupResponse>builder()
                .result(response)
                .build();
    }

    /**
     * Thiết lập 2FA - Bước 2: Xác nhận và bật 2FA
     */
    @PostMapping("/2fa/confirm")
    public ApiResponse<Void> confirm2FA(@RequestBody @Valid TwoFAConfirmRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTwoFactorSecret() == null) {
            return ApiResponse.<Void>builder()
                    .code(400)
                    .message("2FA chưa được thiết lập cho user này")
                    .build();
        }

        boolean isCodeValid = authenticationService.verifyTwoFactorCode(
                user.getTwoFactorSecret(),
                Integer.parseInt(request.getCode())
        );

        if (isCodeValid) {
            authenticationService.enableTwoFactor(user);
            return ApiResponse.<Void>builder()
                    .message("2FA đã được bật thành công")
                    .build();
        } else {
            return ApiResponse.<Void>builder()
                    .code(400)
                    .message("Mã xác thực không đúng")
                    .build();
        }
    }

    /**
     * Tắt 2FA
     */
    @PostMapping("/2fa/disable")
    public ApiResponse<Void> disable2FA(@RequestBody @Valid TwoFADisableRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getTwoFactorEnabled()) {
            return ApiResponse.<Void>builder()
                    .code(400)
                    .message("2FA chưa được bật")
                    .build();
        }

        boolean isCodeValid = authenticationService.verifyTwoFactorCode(
                user.getTwoFactorSecret(),
                Integer.parseInt(request.getCode())
        );

        if (isCodeValid) {
            authenticationService.disableTwoFactor(user);
            return ApiResponse.<Void>builder()
                    .message("2FA đã được tắt thành công")
                    .build();
        } else {
            return ApiResponse.<Void>builder()
                    .code(400)
                    .message("Mã xác thực không đúng")
                    .build();
        }
    }

    /**
     * Tạo QR URL cho Google Authenticator
     */
    private String generateQR(String email, String secret) {
        String issuer = "DiTuSmartFood";
        String otpAuthUrl = String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                issuer, email, secret, issuer
        );

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(otpAuthUrl, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            // Trả về chuỗi Base64 để frontend render <img src="data:image/png;base64,..." />
            return Base64.getEncoder().encodeToString(pngData);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Không thể sinh QR code", e);
        }
    }
}