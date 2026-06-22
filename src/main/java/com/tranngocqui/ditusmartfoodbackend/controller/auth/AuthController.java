package com.tranngocqui.ditusmartfoodbackend.controller.auth;

import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse<AuthResponse>> clientLogin(@RequestBody AuthRequest authRequest) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.clientLogin(authRequest)));
//    }
//
//    @PostMapping("/dashboard-login")
//    public ResponseEntity<ApiResponse<Auth2FAResponse>> dashboardLogin(@RequestBody AuthRequest authRequest) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.dashboardLogin(authRequest)));
//    }
//
//    @PostMapping("/2fa-setup")
//    public ResponseEntity<ApiResponse<Auth2FAResponse>> setup2FA(@RequestBody TwoFASetupRequest twoFASetupRequest) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.setup2FA(twoFASetupRequest.getSetUpToken())));
//    }
//
//    @PostMapping("/2fa-confirm")
//    public ResponseEntity<ApiResponse<Auth2FAResponse>> confirm2FA(@RequestBody TwoFASetupRequest twoFASetupRequest) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.confirm2FA(twoFASetupRequest.getSetUpToken(), twoFASetupRequest.getOtp())));
//    }
//
//    @PostMapping("/verify-2fa")
//    public ResponseEntity<ApiResponse<Auth2FAResponse>> verify2FA(@RequestBody @Valid TwoFAVerifyRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.verify2FA(request.getPreLoginToken(), request.getOtp())));
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<ApiResponse<UserResponse>> getMe(Authentication authentication) {
//        return ResponseEntity.ok(ApiResponse.success(authenticationService.getMe(authentication)));
//    }
//

}
