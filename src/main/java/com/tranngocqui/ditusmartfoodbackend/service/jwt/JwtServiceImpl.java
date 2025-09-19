package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tranngocqui.ditusmartfoodbackend.entity.Jwt;
import com.tranngocqui.ditusmartfoodbackend.enums.TokenType;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.JwtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${spring.security.jwt.secret}")
    private String SIGNER_KEY;

    @Value("${spring.security.jwt.issuer}")
    private String ISSUER;

    private final JwtRepository jwtRepository;

    private final Map<String, String> tempTokenStore = new ConcurrentHashMap<>();

    @Override
    public String generateToken(User user) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .issuer("ditufood.com")
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                    .claim("scope", buildScope(user))
                    .claim("userId", user.getId())
                    .claim("tokenType", TokenType.ACCESS.name())
                    .build();

            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwtClaimsSet.toJSONObject()));

            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateRefreshToken(User user) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .jwtID(UUID.randomUUID().toString())
                    .issuer("ditufood.com")
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(30, ChronoUnit.DAYS)))
                    .claim("tokenType", "REFRESH")
                    .claim("userId", user.getId())
                    .build();

            Jwt jwt = Jwt.builder()
                    .id(UUID.fromString(jwtClaimsSet.getJWTID()))
                    .revoked(false)
                    .expirationDate(
                            LocalDateTime.ofInstant(jwtClaimsSet.getExpirationTime().toInstant(), ZoneId.systemDefault())
                    )
                    .issuedDate(
                            LocalDateTime.ofInstant(jwtClaimsSet.getIssueTime().toInstant(), ZoneId.systemDefault())
                    )
                    .tokenType(TokenType.REFRESH)
                    .user(user)
                    .build();

            jwtRepository.save(jwt);

            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwtClaimsSet.toJSONObject()));
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Cannot create refresh token", e);
        }
    }

    @Override
    public boolean validateToken(String token, String expectedType) {
        try {
            if (token == null || token.isBlank()) {
                log.warn("Token is null or empty");
                return false;
            }

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            if (!signedJWT.verify(verifier)) {
                log.warn("Token signature invalid");
                return false;
            }

            Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (exp == null || exp.before(new Date())) {
                log.warn("Token expired at {}", exp);
                return false;
            }

            String tokenType = signedJWT.getJWTClaimsSet().getStringClaim("tokenType");
            if (!expectedType.equals(tokenType)) {
                log.warn("Token type mismatch: expected {}, got {}", expectedType, tokenType);
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error("Token validation error", e);
            return false;
        }
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(verifier)) return false;

            Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expiry == null || expiry.before(new Date())) return false;

            String tokenType = signedJWT.getJWTClaimsSet().getStringClaim("tokenType");
            return TokenType.valueOf(tokenType) == TokenType.ACCESS;

        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean validateRefreshToken(String token) throws ParseException {
        JWTClaimsSet claimsSet = verifyAndParseToJwtClaimSetIfTrue(token);
        if (claimsSet == null) return false;

        String tokenType = claimsSet.getStringClaim("tokenType");

        if (TokenType.valueOf(tokenType) != TokenType.REFRESH) return false;

        String jti = claimsSet.getJWTID();
        if (jti == null) return false;

        try {
            UUID tokenId = UUID.fromString(jti);
            return jwtRepository.existsByIdAndRevokedFalseAndUserEnabledTrue(tokenId);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    @Override
    public String generateTempToken(User user) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getId().toString())
                    .issuer(ISSUER)
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                    .claim("tokenType", TokenType.TEMP.name())
                    .build();

            JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwtClaimsSet.toJSONObject()));
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            String tempToken = jwsObject.serialize();
            tempTokenStore.put(tempToken, user.getEmail());

            return tempToken;
        } catch (JOSEException e) {
            log.error("Cannot create temp token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String validateTempToken(String tempToken) {
        try {
            if (!tempTokenStore.containsKey(tempToken)) return null;

            SignedJWT signedJWT = SignedJWT.parse(tempToken);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
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

    @Override
    public void removeTempToken(String tempToken) {
        tempTokenStore.remove(tempToken);
    }

    @Override
    public void cleanupExpiredTempTokens() {
        tempTokenStore.entrySet().removeIf(entry -> {
            try {
                SignedJWT signedJWT = SignedJWT.parse(entry.getKey());
                Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
                return expiredTime.before(new Date());
            } catch (Exception e) {
                return true;
            }
        });
    }

    @Override
    public String generateTempTokenSetup2Fa(User user) {
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .issuer(ISSUER)
                    .subject(user.getId().toString())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
                    .claim("tokenType", TokenType.SETUP_2FA_TOKEN.name())
                    .build();

            SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
            signedJWT.sign(new MACSigner(SIGNER_KEY.getBytes()));

            String tempToken = signedJWT.serialize();
            tempTokenStore.put(tempToken, user.getEmail());

            return tempToken;

        } catch (JOSEException e) {
            log.error("Cannot create temp token", e);
            throw new RuntimeException(e);
        }

    }

    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    public UUID getUserIdFromJwt(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));

            if (!signedJWT.verify(verifier)) return null;

            System.out.println("Subject: " + signedJWT.getJWTClaimsSet().getSubject());

            return UUID.fromString(signedJWT.getJWTClaimsSet().getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String generateTempTokenPreLogin2Fa(User user) {

        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .issuer(ISSUER)
                    .subject(user.getId().toString())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
                    .claim("tokenType", TokenType.PRE_LOGIN_TOKEN.name())
                    .build();

            SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);

            signedJWT.sign(new MACSigner(SIGNER_KEY.getBytes()));

            String tempToken = signedJWT.serialize();

            tempTokenStore.put(tempToken, user.getEmail());

            return tempToken;

        } catch (JOSEException e) {
            log.error("Cannot create temp token", e);
            throw new RuntimeException(e);
        }
    }

    private JWTClaimsSet verifyAndParseToJwtClaimSetIfTrue(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));

            if (!signedJWT.verify(verifier)) return null;

            Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expiry == null || expiry.before(new Date())) return null;

            return signedJWT.getJWTClaimsSet();

        } catch (Exception e) {
            return null;
        }
    }
}
