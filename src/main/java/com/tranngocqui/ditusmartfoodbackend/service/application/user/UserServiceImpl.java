package com.tranngocqui.ditusmartfoodbackend.service.application.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.service.domain.user.UserDomainService;
import com.tranngocqui.ditusmartfoodbackend.ultis.UUIDUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public Page<UserAdminResponse> getAll(Pageable pageable) {
        return userDomainService.getUsers(pageable).map(userMapper::toUserAdminResponse);
    }

    @Override
    public void deleteById(String id) {
        userDomainService.deleteUserById(id);
    }

    @Override
    public UserAdminProfileResponse getUser(String id) {
        return userMapper.toUserAdminProfileResponse(userDomainService.getByIdOrThrow(id));
    }

    @Override
    @Transactional
    public UserAdminProfileResponse update(String id, UserAdminUpdateRequest request) {
        User user = userDomainService.getByIdOrThrow(id);

        if (request.roles() != null) {
            var roles = roleRepository.findAllById(UUIDUtils.fromSetString(request.roles()));

            if (request.roles().size() != roles.size()) {
                throw new AppException(ErrorCode.ROLE_NOT_FOUND);
            }

            user.setRoles(new HashSet<>(roles));
        }

        if (request.password() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userMapper.toUserAdminProfileResponse(userDomainService.createUser(user));
    }

    @Override
    public UserAdminResponse create(UserAdminRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());

        if (user.isEmpty()) {
            return userMapper.toUserAdminResponse(userDomainService.createUser(userMapper.toUser(request)));
        }

        if (user.get().getDeleted() == false) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User deletedUser = user.get();

        deletedUser.setDeleted(false);

        deletedUser.setDeletedAt(null);

        userMapper.update(request, deletedUser);

        return userMapper.toUserAdminResponse(userDomainService.createUser(deletedUser));

    }

}
