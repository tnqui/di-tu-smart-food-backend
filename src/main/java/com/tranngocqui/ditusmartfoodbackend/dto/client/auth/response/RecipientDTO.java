package com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response;

import lombok.Builder;

@Builder
public record RecipientDTO(
        String name,
        String phone,
        String address
) {
}