//package com.tranngocqui.ditusmartfoodbackend.tempservice.application.jwt;
//
//import com.tranngocqui.ditusmartfoodbackend.entity.User;
//
//import java.text.ParseException;
//
//public interface JwtService {
//
//    String generateToken(User user);
//
//    String generateRefreshToken(User user);
//
//    boolean validateToken(String token, String expectedType);
//
//    boolean validateAccessToken(String token) throws ParseException;
//
//    boolean validateRefreshToken(String token) throws ParseException;
//
//    String generateTempToken(User user);
//
//    String validateTempToken(String tempToken);
//
//    void removeTempToken(String tempToken);
//
//    void cleanupExpiredTempTokens();
//
//    String generateTempTokenSetup2Fa(User user);
//
//    String getUserIdFromJwt(String token);
//
//    String generateTempTokenPreLogin2Fa(User user);
//
//}
