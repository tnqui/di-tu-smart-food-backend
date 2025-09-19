package com.tranngocqui.ditusmartfoodbackend.service.clientservice;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.dto.client.request.AuthClientRequest;
import com.tranngocqui.ditusmartfoodbackend.dto.client.response.AuthClientResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.mapper.AddressMapper;
import com.tranngocqui.ditusmartfoodbackend.mapper.ClientMapper;
import com.tranngocqui.ditusmartfoodbackend.service.jwt.JwtServiceImpl;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthClientService {
    private final UserService userService;
    private final JwtServiceImpl jwtService;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final AddressMapper addressMapper;
    private final AuthenticationManager authenticationManager;


    public ApiResponse<AuthClientResponse> login(AuthClientRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword())
        );

        User user = ((CustomUserDetails) auth.getPrincipal()).getUser();

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        AuthClientResponse response = clientMapper.toAuthClientResponse(user);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return ApiResponse.<AuthClientResponse>builder()
                .result(response)
                .build();

    }


}
