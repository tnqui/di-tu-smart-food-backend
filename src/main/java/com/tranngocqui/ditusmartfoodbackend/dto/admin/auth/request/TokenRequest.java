package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Builder;

@Builder
public record TokenRequest(
        String email,
        String phone,
        Integer code,
        String password
) {
}