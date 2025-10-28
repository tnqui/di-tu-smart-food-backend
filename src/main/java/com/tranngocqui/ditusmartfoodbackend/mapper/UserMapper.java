package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    void update(UserAdminUpdateRequest request, @MappingTarget User user);


    @Mapping(target = "roles", ignore = true)
    void update(UserAdminRequest request, @MappingTarget User user);


    @Mapping(target = "roles", ignore = true)
    User toUser(UserAdminRequest request);

    User toUser(RegisterRequest request);

    UserResponse toUserResponse(User user);

    UserAdminResponse toUserAdminResponse(User user);

    UserAdminProfileResponse toUserAdminProfileResponse(User user);

}
