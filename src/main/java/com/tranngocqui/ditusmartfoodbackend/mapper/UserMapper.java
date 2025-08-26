package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.role.response.RoleWithPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    //    User toUser(UserCreateRequest request);
    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //    User toUser(UserCreateRequest request);
    User toUser(TokenRequest request);

    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest request);

    User toUser(RegisterRequest request);

    UserResponse toUserResponse(User user);

    UserProfileResponse toUserProfileResponse(User user);

    RoleWithPermissionsResponse  toRoleWithPermissionsResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

}
