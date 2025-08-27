package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDashboardRequest {
    private String email;
    private String password;
    private Integer twoFactorCode;
}
