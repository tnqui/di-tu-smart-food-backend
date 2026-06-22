package com.tranngocqui.ditusmartfoodbackend.service.user;


import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User save(User user);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    User findByEmail(String email);

    User findById(String id);

    UserAdminResponse create(UserAdminRequest request);

    UserAdminProfileResponse getUser(String id);

    UserAdminProfileResponse update(String id, UserAdminUpdateRequest request);

    Page<UserAdminResponse> getAll(Pageable pageable);

    void deleteById(String id);

}
