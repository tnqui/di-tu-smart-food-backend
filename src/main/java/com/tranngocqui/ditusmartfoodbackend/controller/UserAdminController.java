package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserResponse;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
    private final UserService userService;

//    @PostMapping
//    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
//        UserCreateResponse response = userService.createUser(request);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody @Valid UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.create(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAll())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserProfileResponse> getUser(@PathVariable("userId") UUID id) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @PatchMapping("/{userId}")
    ApiResponse<UserProfileResponse> updateUser(@PathVariable UUID userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userService.update(userId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<UserResponse>> getUsersPagination(
            @PageableDefault(size = 10, page = 0, sort = "fullName") Pageable pageable) {
        return ApiResponse.<Page<UserResponse>>builder()
                .result(userService.getUsersPagination(pageable))
                .build();
    }


//    @PatchMapping("/userId")
//    ApiResponse<UserResponse> updateUsers(@RequestBody UUID userIds, @RequestBody UserUpdateRequest request) {
//        return ApiResponse.<UserResponse>builder()
//                .result(userService.update(userIds, request))
//                .build();
//    }

    @DeleteMapping("/{userId}")
    ApiResponse<Void> deleteUser(@PathVariable UUID userId) {
        userService.delete(userId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
