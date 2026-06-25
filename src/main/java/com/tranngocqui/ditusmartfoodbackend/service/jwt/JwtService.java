package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtService {
    String generateAccessToken(User user, String sessionId);

    String generateRefreshToken(User user, String sessionId);

    boolean isTokenValid(String token);

    String extractSubjectFromToken(String token);

    String extractRoleFromToken(String token);

    Jwt decode(String token);
}
