package com.tranngocqui.ditusmartfoodbackend.service.serviceimpl;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setEmail(userCreateRequest.getEmail());
        user.setPhone(userCreateRequest.getPhone());
        user.setPasswordHash(passwordEncoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);
    }
}
