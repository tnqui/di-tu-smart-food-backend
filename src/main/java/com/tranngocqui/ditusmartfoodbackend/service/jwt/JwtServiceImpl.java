package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.tranngocqui.ditusmartfoodbackend.configuration.jwt.JwtProperties;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(User user, String sessionId) {
        Instant now = Instant.now();

        var roles = user.getRoles().stream().map(Enum::name).collect(Collectors.joining(" ")) + user.getPermissions().stream().map(Permission::getName).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .claim("sid", sessionId)
                .claim("scope", roles)
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
    public String extractSubjectFromToken(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    @Override
    public String extractRoleFromToken(String token) {
        return jwtDecoder.decode(token).getClaim("role");
    }
    

}
