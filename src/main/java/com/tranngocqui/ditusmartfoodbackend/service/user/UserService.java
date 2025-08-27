package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.dashboard.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);

    List<UserResponse> getAll();

    UserProfileResponse getUser(UUID id);

    UserProfileResponse getUserByEmailOrPhone(String email, String phone);

    UserProfileResponse update(UUID id, UserUpdateRequest request);

    UserResponse updateProfile(UUID id, UserUpdateRequest request);

    //    User updateUser(UUID id, UserUpdateRequest request);
    void delete(UUID id);

    Page<UserResponse> getUsersPagination(Pageable pageable);

    Optional<User> findByEmail(String email);

    User save(User user);
}
