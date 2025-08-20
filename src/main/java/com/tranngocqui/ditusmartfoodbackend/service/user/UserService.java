package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);
    List<UserResponse> getAll();
    UserResponse getUser(UUID id);
    UserResponse update(UUID id, UserUpdateRequest request);

    //    User updateUser(UUID id, UserUpdateRequest request);
    void delete(UUID id);

}
