package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    @PreAuthorize("hasAnyAuthority('CREATE_USER','ROLE_ADMIN')")
    public UserAdminResponse create(UserAdminRequest request) {
        User user = userMapper.toUser(request);

        if (userRepository.existsByEmail(request.email())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhone(request.phone())) {
            throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
        }

        if (request.roles() != null) {
            var listValidRole = roleRepository.findAllById(UUIDUtils.fromSetString(request.roles()));

            if (listValidRole.size() != request.roles().size()) {
                throw new AppException(ErrorCode.ROLE_NOT_FOUND);
            }
            user.setRoles(new HashSet<>(listValidRole));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setCreatedAt(LocalDateTime.now());

        return userMapper.toUserAdminResponse(userRepository.save(user));
    }

    @Override
    public List<UserAdminResponse> getAll() {
        var users = userRepository.findAll();
        return userMapper.toUserResponseList(users);
    }

    @Override
    public UserAdminProfileResponse getUser(String id) {
        return userMapper.toUserAdminProfileResponse(
                userRepository.findUserProfileById(UUID.fromString(id)).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public UserAdminProfileResponse update(String id, UserUpdateRequest request) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);

        if (request.getRoles() != null) {
            var roles = roleRepository.findAllById(UUIDUtils.fromSetString(request.getRoles()));

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
    public void delete(UUID id) {
        userRepository.existsById(id);

        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }

    @Override
    public Page<UserAdminResponse> getUsersPagination(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toUserAdminResponse);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }



}
