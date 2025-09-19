package com.tranngocqui.ditusmartfoodbackend.controller.client;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.AuthClientRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AuthClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.mapper.ClientMapper;
import com.tranngocqui.ditusmartfoodbackend.service.jwt.JwtServiceImpl;
import com.tranngocqui.ditusmartfoodbackend.service.clientservice.AuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthClientController {

    private final AuthClientService authClientService;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final ClientMapper clientMapper;

    @PostMapping("/login")
    public ApiResponse<AuthClientResponse> login(@RequestBody AuthClientRequest authClientRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authClientRequest.getIdentifier(), authClientRequest.getPassword())
        );

        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        String accessToken = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        AuthClientResponse response = clientMapper.toAuthClientResponse(user);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return ApiResponse.<AuthClientResponse>builder().result(response).build();

    }

//    @PostMapping("/login")
//    ApiResponse<AuthClientResponse> login(@RequestBody AuthClientRequest authClientRequest) {
//        return ApiResponse.<AuthClientResponse>builder()
//                .result(authClientService.login(authClientRequest))
//                .build();
//    };
//
//    @PostMapping("/refresh")
//    ApiResponse<AuthClientResponse> refreshToken(@RequestBody AuthClientRequest authClientRequest) {
//        return ApiResponse.<AuthClientResponse>builder()
//                .result(authClientService.login(authClientRequest))
//                .build();
//    };


}
