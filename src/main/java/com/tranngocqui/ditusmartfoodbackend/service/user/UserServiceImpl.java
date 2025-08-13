package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserUpdateResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new UserCreateResponse(
                user.getId(),
                user.getEmail(),
                user.getPhone()
        );
    }

    @Override
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (request.getPassword()!=null) {
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getFullName()!=null) {
            user.setFullName(request.getFullName());
        }

        if (request.getAvatar()!=null) {

        }

        return new UserUpdateResponse(

        );
    }


}
