package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response.RegisterResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.AuthResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    RegisterResponse toRegisterResponse(User user);

    AuthResponse toAuthResponse(AuthRequest authRequest);

}
