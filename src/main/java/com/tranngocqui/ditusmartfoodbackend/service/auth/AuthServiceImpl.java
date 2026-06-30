package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.tranngocqui.ditusmartfoodbackend.configuration.properties.JwtProperties;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginByEmailAndPasswordRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientLoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.client.AuthClientRegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.service.email.EmailService;
import com.tranngocqui.ditusmartfoodbackend.service.jwt.JwtService;
import com.tranngocqui.ditusmartfoodbackend.service.redis.RedisService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;
    private final RedisService redisService;
    private final JwtService jwtService;
    private final String apiDomain;
    private final JwtProperties jwtProperties;
    private final JwtDecoder jwtDecoder;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserService userService, EmailService emailService, RedisService redisService, JwtService jwtService, @Value("${spring.api-domain}") String apiDomain, JwtProperties jwtProperties, JwtDecoder jwtDecoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.apiDomain = apiDomain;
        this.jwtProperties = jwtProperties;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    @Transactional
    public void clientRegister(AuthClientRegisterRequest request) {

        if (userService.existsByEmail(request.email())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (userService.existsByPhone(request.phone())) {
            throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
        }

        User user = User.clientRegister(request.email(), request.phone(), passwordEncoder.encode(request.password()), request.firstName(), request.lastName());

        User userSaved = userService.save(user);

        String token = UUID.randomUUID().toString();

        String apiRoute = apiDomain + "/auth/register/verify?token=" + token;

        redisService.save(token, userSaved.getId().toString(), Duration.ofMinutes(5));

        System.out.println(apiRoute);

        emailService.sendEmail(request.email(), "Verification Email", apiRoute);


    }

    @Override
    @Transactional
    public void verifyEmailVerificationLink(String token) {
        Object object = redisService.get(token);

        if (object == null) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        UUID userId = UUID.fromString(String.valueOf(object));

        User user = userService.findById(userId);

        user.verifyEmail();

        userService.save(user);

        redisService.delete(token);

    }

    @Override
    public AuthClientLoginResponse clientLoginByEmailAndPassword(AuthClientLoginByEmailAndPasswordRequest request) {
        User user = userService.findByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AppException(ErrorCode.WRONG_LOGIN_INFORMATION);
        }

        if (!user.canLogin()) {
            throw new AppException(ErrorCode.ACCOUNT_DISABLED);
        }

        String sessionId = String.valueOf(UUID.randomUUID());

        redisService.save("session:" + sessionId, user.getId().toString(), Duration.ofSeconds(jwtProperties.accessTokenExpiration()));

        return AuthClientLoginResponse.builder()
                .accessToken(jwtService.generateAccessToken(user, sessionId))
                .refreshToken(jwtService.generateRefreshToken(user, sessionId))
                .build();
    }

    @Override
    public Map<String, String> refresh(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        String sessionId = jwt.getClaim("sid");

        UUID subject = UUID.fromString(jwt.getSubject());

        if (redisService.get("session:" + sessionId) == null) {
            throw new AppException(ErrorCode.SESSION_EXPIRED);
        }

        User user = userService.findById(subject);

        return Map.of("accessToken", jwtService.generateAccessToken(user, sessionId));
    }

}
