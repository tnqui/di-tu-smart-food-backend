package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.TwoFAConfirmRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.TwoFADisableRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.TwoFASetupRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request.TwoFAVerifyRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/test/2fa")
@RequiredArgsConstructor
public class TwoFactorController {

    private final UserService userService;
    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    /**
     * B1: Khởi tạo secret key + QR
     */
    @PostMapping("/setup")
    public ResponseEntity<Map<String, String>> setup2FA(@RequestBody TwoFASetupRequest req) {
        User user = userService.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GoogleAuthenticatorKey key = gAuth.createCredentials();
        user.setTwoFactorSecret(key.getKey());
        userService.save(user);

        String qrUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL(
                "DiTuSmartFood", user.getEmail(), key);

        Map<String, String> response = new HashMap<>();
        response.put("secret", key.getKey());
        response.put("qrUrl", qrUrl);

        return ResponseEntity.ok(response);
    }

    /**
     * B2: Xác nhận và bật 2FA
     */
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm2FA(@RequestBody TwoFAConfirmRequest req) {
        User user = userService.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTwoFactorSecret() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("2FA chưa được setup cho user này");
        }

        boolean isCodeValid = gAuth.authorize(user.getTwoFactorSecret(), req.getCode());
        if (isCodeValid) {
            user.setTwoFactorEnabled(true);
            userService.save(user);
            return ResponseEntity.ok(Map.of("message", "2FA enabled successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
    }

    /**
     * B3: Xác thực khi login
     */
    @PostMapping("/verify")
    public ResponseEntity<?> verify2FA(@RequestBody TwoFAVerifyRequest req) {
        User user = userService.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isTwoFactorEnabled()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("2FA chưa được bật");
        }

        boolean isCodeValid = gAuth.authorize(user.getTwoFactorSecret(), req.getCode());
        if (isCodeValid) {
            return ResponseEntity.ok(Map.of("message", "2FA verification successful"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
    }

    /**
     * B4: Tắt 2FA
     */
    @DeleteMapping
    public ResponseEntity<?> disable2FA(@RequestBody TwoFADisableRequest req) {
        User user = userService.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isCodeValid = gAuth.authorize(user.getTwoFactorSecret(), req.getCode());
        if (isCodeValid) {
            user.setTwoFactorEnabled(false);
            user.setTwoFactorSecret(null);
            userService.save(user);
            return ResponseEntity.ok(Map.of("message", "2FA disabled successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
    }
}
