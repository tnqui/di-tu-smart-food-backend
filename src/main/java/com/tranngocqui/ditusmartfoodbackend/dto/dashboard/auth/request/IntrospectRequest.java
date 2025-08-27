package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import lombok.*;

//Verify token
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {
    private String token;
}
