package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.response;

import lombok.Builder;

@Builder
public record IntrospectResponse(
        boolean valid
) {
}