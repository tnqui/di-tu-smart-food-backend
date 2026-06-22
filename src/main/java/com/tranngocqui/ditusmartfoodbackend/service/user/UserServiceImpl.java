package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserAdminResponse create(UserAdminRequest request) {
        return null;
    }

    @Override
    public UserAdminProfileResponse getUser(String id) {
        return null;
    }

    @Override
    public UserAdminProfileResponse update(String id, UserAdminUpdateRequest request) {
        return null;
    }

    @Override
    public Page<UserAdminResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
    
}
