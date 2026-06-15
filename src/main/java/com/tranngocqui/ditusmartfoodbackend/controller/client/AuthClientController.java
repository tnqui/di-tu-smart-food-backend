package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.configuration.jwt.JwtProperties;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginByEmailAndPasswordRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientRegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AuthClientResponse;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthClientController {

    private final AuthService authService;
    private final JwtProperties jwtProperties;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthClientResponse>> login(@RequestBody AuthClientRegisterRequest request) {
        authService.clientRegister(request);
        return ResponseEntity.ok(ApiResponse.success("Verification link has been sent to your email!"));
    }

    @GetMapping("/register/verify")
    public ResponseEntity<ApiResponse<AuthClientResponse>> verifyEmail(@RequestParam("token") String token) {
        authService.verifyEmailVerificationLink(token);
        return ResponseEntity.ok(ApiResponse.success("Successfully verified your email!"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthClientLoginResponse>> login(@RequestBody AuthClientLoginByEmailAndPasswordRequest request, HttpServletResponse response) {
        var token = authService.clientLoginByEmailAndPassword(request);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", token.refreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(jwtProperties.refreshTokenExpiration())
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(ApiResponse.success(AuthClientLoginResponse.builder()
                .accessToken(token.accessToken())
                .build()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<?>> refresh(@CookieValue("refresh_token") String token) {
        return ResponseEntity.ok(ApiResponse.success(authService.refresh(token)));
    }

}
