package com.tranngocqui.ditusmartfoodbackend.controller;

import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.IntrospectRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.TokenResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthenticationService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/token")
    ApiResponse<TokenResponse> login(@RequestBody TokenRequest loginRequest) {
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



//    @PostMapping("/logout")
//    public ResponseEntity<AuthenticationResponse> logout(@RequestBody LogoutRequest logoutRequestRequest) {
//        return ResponseEntity.ok(authenticationService.logout(logoutRequestRequest));
//    }

}
