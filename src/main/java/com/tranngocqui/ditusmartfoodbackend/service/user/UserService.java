package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserAdminResponse create(UserAdminRequest request);

    List<UserAdminResponse> getAll();

    UserAdminProfileResponse getUser(String id);

    UserAdminProfileResponse update(String id, UserUpdateRequest request);

    void delete(UUID id);

    Page<UserAdminResponse> getUsersPagination(Pageable pageable);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    User save(User user);

    User findById(UUID id);

}
