package com.tranngocqui.ditusmartfoodbackend.tempservice.application.user;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    UserAdminResponse create(UserAdminRequest request);

    UserAdminProfileResponse getUser(String id);

    UserAdminProfileResponse update(String id, UserAdminUpdateRequest request);

    Page<UserAdminResponse> getAll(Pageable pageable);

    void deleteById(String id);

    Optional<User> getByEmail(String email);

    Optional<User> getByPhone(String phone);

}
