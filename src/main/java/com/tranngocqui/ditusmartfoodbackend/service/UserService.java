package com.tranngocqui.ditusmartfoodbackend.service;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);
}
