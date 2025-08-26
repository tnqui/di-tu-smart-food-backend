package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.IntrospectRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LogoutRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.TokenResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.IntrospectResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.RegisterResponse;
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
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationMapper authenticationMapper;


    @NonFinal
    @Value("${spring.security.jwt.secret}")
    protected String SIGNER_KEY;

    @Override
    public TokenResponse token(TokenRequest request) {
        User user = userRepository.findUserProfileByEmailOrPhone(request.getEmailOrPhone(), request.getEmailOrPhone())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.LOGIN_FAILED);
        }

        var token = generateToken(user);

        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                //issuer thường là domain
                .issuer("ditufood.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))

                .claim("scope", buildScope(user))
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

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiredTime.after(new Date()))
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
}
