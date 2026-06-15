package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Builder;

@Builder
public record IntrospectRequest(
        String token
) {
}