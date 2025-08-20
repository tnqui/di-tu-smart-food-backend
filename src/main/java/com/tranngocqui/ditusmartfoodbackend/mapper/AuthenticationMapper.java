package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.nimbusds.jose.JOSEException;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.response.RegisterResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.Mapper;

import java.text.ParseException;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    RegisterResponse toRegisterResponse(User user );

}
