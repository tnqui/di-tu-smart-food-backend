package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.tranngocqui.ditusmartfoodbackend.entity.User;

public interface JwtService {
    String generateAccessToken(User user, String sessionId);

    String generateRefreshToken(User user, String sessionId);

    String extractSubjectFromToken(String token);

    String extractRoleFromToken(String token);
}
