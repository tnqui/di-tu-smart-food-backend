package com.tranngocqui.ditusmartfoodbackend.service.user;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserUpdateResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserCreateResponse createUser(UserCreateRequest request);

    UserUpdateResponse updateUser(Long id, UserUpdateRequest request);

}
