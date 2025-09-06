package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.*;

//Verify token
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {
    private String token;
}
