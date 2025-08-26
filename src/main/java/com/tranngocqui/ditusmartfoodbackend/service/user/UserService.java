package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

}
