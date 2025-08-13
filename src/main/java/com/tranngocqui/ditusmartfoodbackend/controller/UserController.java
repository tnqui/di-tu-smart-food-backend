package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.request.UserUpdateRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserCreateResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserUpdateResponse;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
    ) {
        UserUpdateResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }



}
