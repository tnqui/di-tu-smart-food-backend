package com.tranngocqui.ditusmartfoodbackend.service.jwt;

import com.tranngocqui.ditusmartfoodbackend.entity.User;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface JwtService {

    String generateToken(User user);

    String generateRefreshToken(User user);

    boolean validateToken(String token, String expectedType);

    boolean validateAccessToken(String token) throws ParseException;

    boolean validateRefreshToken(String token) throws ParseException;

    String generateTempToken(User user);

    String validateTempToken(String tempToken);

    void removeTempToken(String tempToken);

    void cleanupExpiredTempTokens();

    String generateTempTokenSetup2Fa(User user);

    UUID getUserIdFromJwt(String token);

    String generateTempTokenPreLogin2Fa(User user);

}
