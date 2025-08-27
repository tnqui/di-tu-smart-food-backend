package com.tranngocqui.ditusmartfoodbackend.controller;

import com.google.zxing.WriterException;
import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.IntrospectRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.LoginDashboardRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.TokenResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.TwoFactorAuthenticationToken;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthenticationService;
import com.tranngocqui.ditusmartfoodbackend.service.auth.GoogleAuthenticatorService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final GoogleAuthenticatorService googleAuthenticatorService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/token")
    ApiResponse<TokenResponse> token(@RequestBody TokenRequest loginRequest) {
        var result = authenticationService.token(loginRequest);
        return ApiResponse.<TokenResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/identity")
    public ApiResponse<UserProfileResponse> identify(@AuthenticationPrincipal Jwt jwt) {
        String emailOrPhone = jwt.getClaim("sub"); // "admin@gmail.com"
        return ApiResponse.<UserProfileResponse>builder()
                .result(userService.getUserByEmailOrPhone(emailOrPhone, emailOrPhone))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/register")
    ApiResponse register(@Valid @RequestBody RegisterRequest request) {
        var result = authenticationService.register(request);
        return ApiResponse.builder()
                .result(result)
                .build();
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) Integer code) {

        try {
            Authentication authentication;

            if (code != null) {
                // Đăng nhập với 2FA
                authentication = authenticationManager.authenticate(
                        new TwoFactorAuthenticationToken(username, password, code)
                );
            } else {
                // Đăng nhập bình thường
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Đăng nhập thành công");

            return ResponseEntity.ok(response);

        } catch (AppException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "2fa_required");
            response.put("message", "Yêu cầu mã 2FA");
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);

        } catch (BadCredentialsException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

//    @PostMapping("/logout")
//    public ResponseEntity<AuthenticationResponse> logout(@RequestBody LogoutRequest logoutRequestRequest) {
//        return ResponseEntity.ok(authenticationService.logout(logoutRequestRequest));
//    }

}
