package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Builder;

@Builder
public record RecipientDTO(
        String name,
        String phone,
        String address
) {
}