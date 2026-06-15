package com.tranngocqui.ditusmartfoodbackend.service.otp;

public interface OtpService {
    void saveOtp(String email, String otp);

    boolean verifyOtp(String email, String otp);

    void deleteOtp(String email);
}
