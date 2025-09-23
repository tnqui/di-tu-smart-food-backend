package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.enums.RoleConstants;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request.*;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.*;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.Auth2FAResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.TokenType;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.AuthenticationMapper;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.service.jwt.JwtService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import com.tranngocqui.ditusmartfoodbackend.ultis.RoleChecker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationMapper authenticationMapper;
    private final GoogleAuthenticatorService googleAuthenticatorService;
    private final JwtService jwtService;

    @NonFinal
    @Value("${spring.security.jwt.secret}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${spring.security.jwt.issuer}")
    private String ISSUER;

    @Value("${app.name}")
    private String appName;

    @Value("${app.totp.algorithm}")
    private String algorithm;

    @Value("${app.totp.digits}")
    private int digits;

    @Value("${app.totp.period}")
    private int period;

    private static final String TWO_FACTOR_URI = "otpauth://totp/%s:%s?secret=%s&issuer=%s&algorithm=SHA1&digits=6&period=30";


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
        String email = jwtService.validateTempToken(request.getTempToken());

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
        jwtService.removeTempToken(request.getTempToken());

        // Tạo token chính thức
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .message("Đăng nhập thành công")
                .build();
    }

    @Override
    public boolean authenticate() {
        return false;
    }

    @Override
    public AuthResponse clientLogin(AuthRequest authRequest) {
        User user = userService.findByEmail(authRequest.getIdentifier())
                .or(() -> userService.findByPhone(authRequest.getIdentifier()))
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD));

        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        if (RoleChecker.hasAnyRole(roles, RoleConstants.STAFF_ROLES)) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        if (!validatePassword(authRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        AuthResponse response = new AuthResponse();
        response.setAccessToken(jwtService.generateToken(user));
        response.setRefreshToken(jwtService.generateRefreshToken(user));
        return response;
    }

    @Override
    public Auth2FAResponse dashboardLogin(AuthRequest authRequest) {

        User user = userService.findByEmail(authRequest.getIdentifier())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD));

        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        if (!RoleChecker.hasAnyRole(roles, RoleConstants.STAFF_ROLES)) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        if (!validatePassword(authRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        if (Boolean.TRUE.equals(user.getTwoFactorEnabled()) && user.getTwoFactorSecret() != null) {
            String verify2FaToken = jwtService.generateTempTokenPreLogin2Fa(user);

            return Auth2FAResponse.builder()
                    .verify2FaToken(verify2FaToken)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Vui lòng nhập mã OTP để hoàn tất đăng nhập")
                    .build();
        } else {
            String setup2FaToken = jwtService.generateTempTokenSetup2Fa(user);

            return Auth2FAResponse.builder()
                    .setup2FaToken(setup2FaToken)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Bạn cần bật 2FA để truy cập dashboard")
                    .build();
        }
    }

    public String generateSetupToken(String userId) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userId)
                    .claim("tokenType", TokenType.TEMP.name())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                    .build();

            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(claimsSet.toJSONObject()));
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Cannot create setup token", e);
        }
    }

    public JWTClaimsSet parseToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            if (!signedJWT.verify(verifier)) {
                throw new SecurityException("Invalid signature");
            }

            return signedJWT.getJWTClaimsSet();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    @Override
    public Auth2FAResponse setup2FA(String setup2FaToken) {

        if (!jwtService.validateToken(setup2FaToken, TokenType.SETUP_2FA_TOKEN.name())) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }

        UUID userId = jwtService.getUserIdFromJwt(setup2FaToken);

        if (userId == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        User user = userService.findById(userId);

        String secret = generateTwoFactorSecret(user);

        user.setTwoFactorSecret(secret);

        user.setTwoFactorEnabled(false);

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        String qrCode = googleAuthenticatorService.generateQRCodeUrl(user.getEmail(), secret, appName);

        return Auth2FAResponse.builder()
                .qrAuthenticationSetup(qrCode)
                .requires2FA(true)
                .build();
    }

    @Override
    public Auth2FAResponse confirm2FA(String twoFactorSetupToken, String code) {
        if (!jwtService.validateToken(twoFactorSetupToken, TokenType.SETUP_2FA_TOKEN.name())) {

            System.out.println(twoFactorSetupToken);

            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        UUID userId = jwtService.getUserIdFromJwt(twoFactorSetupToken);
        System.out.println("userId = " + userId);

        if (userId == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        User user = userService.findById(userId);

        if (user.getTwoFactorSecret() == null) {
            throw new AppException(ErrorCode.TWO_FACTOR_REQUIRED);
        }

        int otp;

        try {
            otp = Integer.parseInt(code);
        } catch (NumberFormatException e) {
            throw new AppException(ErrorCode.TWO_FACTOR_MISMATCH);
        }

        if (!googleAuthenticatorService.verifyCode(user.getTwoFactorSecret(), otp)) {
            throw new AppException(ErrorCode.TWO_FACTOR_MISMATCH);
        }

        if (!user.getTwoFactorEnabled()) {
            user.setTwoFactorEnabled(true);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }

        return Auth2FAResponse.builder()
                .message("Successfully confirmed 2FA")
                .build();
    }

    @Override
    public Auth2FAResponse verify2FA(String preLoginToken, String otp) {

        if (!jwtService.validateToken(preLoginToken, TokenType.PRE_LOGIN_TOKEN.name())) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        UUID userId = jwtService.getUserIdFromJwt(preLoginToken);

        if (userId == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        User user = userRepository.findWithRoleAndPermissionById((userId));

        int otpNumber;

        try {
            otpNumber = Integer.parseInt(otp);
        } catch (NumberFormatException e) {
            throw new AppException(ErrorCode.TWO_FACTOR_MISMATCH);
        }

        if (!googleAuthenticatorService.verifyCode(user.getTwoFactorSecret(), otpNumber)) {
            throw new AppException(ErrorCode.TWO_FACTOR_MISMATCH);
        }


        return Auth2FAResponse.builder()
                .message("Successfully verified 2FA")
                .authenticated(true)
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    @Override
    public UserResponse getMe(Authentication authentication) {

        if (!(authentication.getPrincipal() instanceof CustomUserDetails customUserDetails)) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        User user = customUserDetails.getUser();

        return userMapper.toUserResponse(user);
    }


    @Override
    public TokenResponse token(TokenRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.LOGIN_FAILED);
        }

        if (Boolean.TRUE.equals(user.getTwoFactorEnabled())) {
            String tempToken = jwtService.generateTempToken(user);
            return TokenResponse.builder()
                    .tempToken(tempToken)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Vui lòng nhập mã OTP để hoàn tất đăng nhập")
                    .build();
        } else {
            String tempToken = jwtService.generateTempToken(user);

            String secret = generateTwoFactorSecret(user);

            String qrCode = googleAuthenticatorService.generateQRCodeUrl(user.getEmail(), secret, appName);

            return TokenResponse.builder()
                    .tempToken(tempToken)
                    .qrAuthenticationSetup(qrCode)
                    .authenticated(false)
                    .requires2FA(true)
                    .message("Bạn cần bật 2FA để truy cập dashboard")
                    .build();
        }
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
        return googleAuthenticatorService.generateSecretKey();
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
}