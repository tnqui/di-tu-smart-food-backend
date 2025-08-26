package com.tranngocqui.ditusmartfoodbackend.controller;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.auth.request.TokenRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.user.response.UserProfileResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.mapper.UserMapper;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/test")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
