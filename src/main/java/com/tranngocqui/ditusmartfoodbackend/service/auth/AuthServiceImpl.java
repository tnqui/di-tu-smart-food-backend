package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LoginRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.LogoutRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LoginResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.LogoutResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.exception.BadRequestException;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getUsername());

        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByPhone(loginRequest.getUsername());
        }

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
                Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
                LoginResponse response = new LoginResponse();
                response.setId(user.getId());
                response.setMessage("Login successful");
                response.setRoles(roles);
                return new LoginResponse(
                        response.getId(),
                        response.getToken(),
                        response.getRoles(),
                        response.getMessage()
                );
            }
        }
        throw new BadRequestException("Invalid username or password");
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {

        return null;
    }
}
