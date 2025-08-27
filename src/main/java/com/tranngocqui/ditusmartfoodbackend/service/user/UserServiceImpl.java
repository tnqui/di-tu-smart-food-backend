package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.RoleMapper;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleMapper roleMapper;

    @Override
    @PreAuthorize("hasAnyAuthority('CREATE_USER','ROLE_ADMIN')")
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
        }

        var listValidRole = roleRepository.findAllById(request.getRoles());

        if (listValidRole.size() != request.getRoles().size()) {
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(new HashSet<>(listValidRole));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAll() {
        var users = userRepository.findAll();
        return userMapper.toUserResponseList(users);
    }

    @Override
    public UserProfileResponse getUser(UUID id) {
        return userMapper.toUserProfileResponse(
                userRepository.findUserProfileById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserProfileResponse getUserByEmailOrPhone(String email, String phone) {
        return userMapper.toUserProfileResponse(
                userRepository.findUserProfileByEmailOrPhone(email, phone).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserProfileResponse update(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        if (request.getRoles() != null) {
            var roles = roleRepository.findAllById(request.getRoles());

            if (request.getRoles().size() != roles.size()) {
                throw new AppException(ErrorCode.ROLE_NOT_FOUND);
            }

            user.setRoles(new HashSet<>(roles));
        }

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);

        return getUser(id);
    }

    @Override
    public UserResponse updateProfile(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return null;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserResponse> getUsersPagination(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        List<UserResponse> dtos = userMapper.toUserResponseList(users.getContent());

        return new PageImpl<>(dtos, pageable, users.getTotalElements());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public Page<UserResponse> getUsersPagination(int _start, int _end) {
//
//        Page<User> page = userRepository.findAll(PageRequest.of(_start, _end));
//
//        return userMapper.toUserResponse(page);
//    }

}
