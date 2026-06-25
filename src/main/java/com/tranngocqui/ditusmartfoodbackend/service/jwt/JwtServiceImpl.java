package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.tranngocqui.ditusmartfoodbackend.configuration.properties.JwtProperties;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(User user, String sessionId) {
        Instant now = Instant.now();
        List<String> roles = user.getRoles().stream().map(Enum::name).toList();
        List<String> permissions = user.getPermissions().stream().map(Permission::getName).toList();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .claim("sid", sessionId)
                .claim("roles", roles)
                .claim("permissions", permissions)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtProperties.accessTokenExpiration()))
                .issuer(jwtProperties.issuer())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(jwtProperties.algorithm()).build();

        JwtEncoderParameters params = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
        return jwtEncoder.encode(params).getTokenValue();
    }

    @Override
    public String generateRefreshToken(User user, String sessionId) {
        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .claim("sid", sessionId)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtProperties.refreshTokenExpiration()))
                .issuer(jwtProperties.issuer())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(jwtProperties.algorithm()).build();

        JwtEncoderParameters params = JwtEncoderParameters.from(jwsHeader, jwtClaimsSet);
        return jwtEncoder.encode(params).getTokenValue();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            jwtDecoder.decode(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String extractSubjectFromToken(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    @Override
    public String extractRoleFromToken(String token) {
        return jwtDecoder.decode(token).getClaim("role");
    }

    @Override
    public Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }

}
