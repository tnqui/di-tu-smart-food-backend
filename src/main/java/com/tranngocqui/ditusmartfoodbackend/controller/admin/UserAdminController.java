package com.tranngocqui.ditusmartfoodbackend.controller.admin;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.request.UserAdminUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.admin.user.response.UserAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.service.application.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
    private final UserService userService;

    @PostMapping
    ApiResponse<UserAdminResponse> create(@RequestBody @Valid UserAdminRequest request) {
        return ApiResponse.<UserAdminResponse>builder()
                .result(userService.create(request))
                .build();
    }

    @GetMapping("/me/{userId}")
    ApiResponse<UserAdminProfileResponse> getUser(@PathVariable("userId") String id) {
        return ApiResponse.<UserAdminProfileResponse>builder()
                .result(userService.getUser(id))
                .build();
    }

    @PatchMapping("/{userId}")
    ApiResponse<UserAdminProfileResponse> update(@PathVariable String userId, @RequestBody UserAdminUpdateRequest request) {
        return ApiResponse.<UserAdminProfileResponse>builder()
                .result(userService.update(userId, request))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<UserAdminResponse>> getUsersPagination(
            @PageableDefault(size = 10, page = 0, sort = "fullName") Pageable pageable) {
        return ApiResponse.<Page<UserAdminResponse>>builder()
                .result(userService.getAll(pageable))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<Void> deleteUser(@PathVariable String userId) {
        userService.deleteById(userId);
        return ApiResponse.<Void>builder()
                .message("Successfully deleted user")
                .build();
    }
}
