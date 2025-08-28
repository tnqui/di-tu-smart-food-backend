package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
//    private User user;
    private String message;
    private Long expiresIn;
}
