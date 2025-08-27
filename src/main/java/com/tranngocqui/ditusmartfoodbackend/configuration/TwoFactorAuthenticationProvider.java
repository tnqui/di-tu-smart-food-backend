package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.TwoFactorAuthenticationToken;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.service.auth.GoogleAuthenticatorService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TwoFactorAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final GoogleAuthenticatorService googleAuthService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findByEmail(username).orElseThrow(() -> new BadCredentialsException("Username not found"));

        if (user == null) {
            throw new BadCredentialsException("User không tồn tại");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Mật khẩu không đúng");
        }

        // Nếu user chưa bật 2FA, cho phép đăng nhập bình thường
        if (!user.isTwoFactorEnabled()) {
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }

        // Nếu có 2FA, yêu cầu mã xác thực
        if (authentication instanceof TwoFactorAuthenticationToken) {
            TwoFactorAuthenticationToken twoFactorToken = (TwoFactorAuthenticationToken) authentication;
            int code = twoFactorToken.getCode();

            if (googleAuthService.verifyCode(user.getTwoFactorSecret(), code)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            } else {
                throw new AppException(ErrorCode.TWO_FACTOR_MISMATCH);
            }
        }

        throw new AppException(ErrorCode.TWO_FACTOR_REQUIRED);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class) ||
                authentication.equals(TwoFactorAuthenticationToken.class);
    }
}
