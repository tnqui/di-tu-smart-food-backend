package com.tranngocqui.ditusmartfoodbackend.mapper;

import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.RegisterRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.permission.response.PermissionResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.role.response.RoleWithoutPermissionsResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    //    User toUser(UserCreateRequest request);
    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //    User toUser(UserCreateRequest request);
    User toUser(TokenRequest request);

    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest request);

    User toUser(RegisterRequest request);

    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(User user);

    RoleWithoutPermissionsResponse toRoleResponse(Role role);

    List<UserResponse> toUserResponse(List<User> users);

}
