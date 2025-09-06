package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.*;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.AuthenticationMapper;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationMapper authenticationMapper;
    private final GoogleAuthenticatorService googleAuthenticatorService;

    @NonFinal
    @Value("${spring.security.jwt.secret}")
    protected String SIGNER_KEY;

    // Lưu trữ temporary tokens cho 2FA
    private final Map<String, String> tempTokenStore = new ConcurrentHashMap<>();

    @Override
    public TokenResponse token(TokenRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.LOGIN_FAILED);
        }

        if (Boolean.TRUE.equals(user.getTwoFactorEnabled())) {
            // Đã bật 2FA -> chỉ trả tempToken, chưa cấp token chính thức
            String tempToken = generateTempToken(user);
            return TokenResponse.builder()
                    .tempToken(tempToken)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Vui lòng nhập mã OTP để hoàn tất đăng nhập")
                    .build();
        } else {
            // Chưa bật 2FA -> cấp token chính thức luôn
            String tempToken = generateTempToken(user);
            String secret = generateTwoFactorSecret(user); // sinh secret lưu DB

            return TokenResponse.builder()
                    .tempToken(tempToken)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Bạn cần bật 2FA để truy cập dashboard")
                    .build();
        }
    }


//    public TokenResponse token(TokenRequest request) {
//        User user = userRepository.findUserProfileByEmailOrPhone(request.getEmail(), request.getPhone())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//
//        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
//
//        if (!authenticated) {
//            throw new AppException(ErrorCode.LOGIN_FAILED);
//        }
//
//        // Kiểm tra xem user có bật 2FA không
//        if (user.getTwoFactorEnabled()) {
//            // Tạo temporary token thay vì token chính thức
//            String tempToken = generateTempToken(user);
//
//            return TokenResponse.builder()
//                    .tempToken(tempToken)
//                    .authenticated(false)
//                    .requires2FA(true)
//                    .message("Vui lòng nhập mã 2FA để hoàn tất đăng nhập")
//                    .build();
//        } else {
//            // Không có 2FA, tạo token bình thường
//            String token = generateToken(user);
//
//            return TokenResponse.builder()
//                    .token(token)
//                    .authenticated(true)
//                    .requires2FA(false)
//                    .build();
//        }
//    }

    /**
     * Xác thực 2FA và tạo token chính thức
     */
    public TokenResponse verify2FA(TwoFALoginRequest request) {
        // Xác thực temporary token
        String email = validateTempToken(request.getTempToken());

        if (email == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        // Tìm user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Kiểm tra 2FA
        if (!user.getTwoFactorEnabled() || user.getTwoFactorSecret() == null) {
            throw new AppException(ErrorCode.TWO_FACTOR_NOT_ENABLED);
        }

        // Xác thực mã 2FA
        boolean isValidCode = googleAuthenticatorService.verifyCode(
                user.getTwoFactorSecret(),
                Integer.parseInt(request.getCode())
        );

        if (!isValidCode) {
            throw new AppException(ErrorCode.INVALID_TWO_FACTOR_CODE);
        }

        // Xóa temp token
        removeTempToken(request.getTempToken());

        // Tạo token chính thức
        String token = generateToken(user);

        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .requires2FA(false)
                .message("Đăng nhập thành công")
                .build();
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("ditufood.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
//                .claim("scope", buildScope(user))
                .claim("userId", user.getId())
                .claim("tokenType", "ACCESS")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Tạo temporary token cho 2FA (chỉ tồn tại 5 phút)
     */
    private String generateTempToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("ditufood.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .claim("tokenType", "TEMP")
                .claim("userId", user.getId())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            String tempToken = jwsObject.serialize();

            // Lưu temp token vào store
            tempTokenStore.put(tempToken, user.getEmail());

            return tempToken;
        } catch (JOSEException e) {
            log.error("Cannot create temp token", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Xác thực temporary token
     */
    private String validateTempToken(String tempToken) {
        try {
            if (!tempTokenStore.containsKey(tempToken)) {
                return null;
            }

            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(tempToken);

            if (!signedJWT.verify(verifier)) {
                tempTokenStore.remove(tempToken);
                return null;
            }

            Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            String tokenType = signedJWT.getJWTClaimsSet().getStringClaim("tokenType");

            if (expiredTime.before(new Date()) || !"TEMP".equals(tokenType)) {
                tempTokenStore.remove(tempToken);
                return null;
            }

            return signedJWT.getJWTClaimsSet().getSubject();

        } catch (Exception e) {
            log.error("Error validating temp token", e);
            tempTokenStore.remove(tempToken);
            return null;
        }
    }

    /**
     * Xóa temporary token
     */
    private void removeTempToken(String tempToken) {
        tempTokenStore.remove(tempToken);
    }

    @Override
    public boolean authenticate(LogoutRequest logoutRequest) {
        return false;
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        String tokenType = signedJWT.getJWTClaimsSet().getStringClaim("tokenType");

        var verified = signedJWT.verify(verifier) &&
                expiredTime.after(new Date()) &&
                "ACCESS".equals(tokenType);

        return IntrospectResponse.builder()
                .valid(verified)
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        String input = registerRequest.getEmailOrPhone();
        User user = userMapper.toUser(registerRequest);

        if (input.contains("@")) {
            if (userRepository.existsByEmail(input)) {
                throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
            }
            user.setEmail(input);
        } else {
            if (userRepository.existsByPhone(input)) {
                throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
            }
            user.setPhone(input);
        }

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        return authenticationMapper.toRegisterResponse(userRepository.save(user));
    }

    @Override
    public String generateTwoFactorSecret(User user) {
        String secret = googleAuthenticatorService.generateSecretKey();
        user.setTwoFactorSecret(secret);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return secret;
    }

    @Override
    public void enableTwoFactor(User user) {
        user.setTwoFactorEnabled(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void disableTwoFactor(User user) {
        user.setTwoFactorEnabled(false);
        user.setTwoFactorSecret(null);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public boolean verifyTwoFactorCode(String secret, int code) {
        return googleAuthenticatorService.verifyCode(secret, code);
    }

    @Override
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getName());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    /**
     * Cleanup expired temp tokens (nên chạy định kỳ)
     */
    public void cleanupExpiredTempTokens() {
        tempTokenStore.entrySet().removeIf(entry -> {
            try {
                SignedJWT signedJWT = SignedJWT.parse(entry.getKey());
                Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
                return expiredTime.before(new Date());
            } catch (Exception e) {
                return true; // Remove invalid tokens
            }
        });
    }
}
