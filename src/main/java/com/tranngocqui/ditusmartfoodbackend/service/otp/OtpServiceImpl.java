package com.tranngocqui.ditusmartfoodbackend.service.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String PREFIX = "otp:";

    @Override
    public void saveOtp(String email, String otp) {
        redisTemplate.opsForValue().set(PREFIX + email, otp, Duration.ofMinutes(5));
    }

    @Override
    public boolean verifyOtp(String email, String otp) {

        String storedOtp = redisTemplate.opsForValue().get(PREFIX + email);

        if (storedOtp == null) {
            return false;
        }

        deleteOtp(email);

        return storedOtp.equals(otp);
    }

    @Override
    public void deleteOtp(String email) {
        redisTemplate.delete(PREFIX + email);
    }
}
