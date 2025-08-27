package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDashboardResponse {
    boolean requiresTwoFactor;
}
