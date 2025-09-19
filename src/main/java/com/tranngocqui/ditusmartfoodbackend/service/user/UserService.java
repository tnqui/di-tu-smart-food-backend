package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserAdminRequest request);

    List<UserResponse> getAll();

    UserProfileResponse getUser(UUID id);

    UserProfileResponse getUserByEmailOrPhone(String email, String phone);

    UserProfileResponse update(UUID id, UserUpdateRequest request);

    UserResponse updateProfile(UUID id, UserUpdateRequest request);

    //    User updateUser(UUID id, UserUpdateRequest request);
    void delete(UUID id);

    Page<UserResponse> getUsersPagination(Pageable pageable);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    User save(User user);

    boolean existsById(UUID id);

    Optional<User> findById(UUID id);

    Optional<User> getUserProfileByEmailOrPhone(String email, String phone);

}
