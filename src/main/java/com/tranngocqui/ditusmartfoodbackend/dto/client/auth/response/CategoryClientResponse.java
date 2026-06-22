package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

@Builder
public record CategoryClientResponse(
        String id,
        String name,
        String imageUrl
) {
}