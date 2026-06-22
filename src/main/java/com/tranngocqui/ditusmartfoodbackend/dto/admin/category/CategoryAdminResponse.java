package com.tranngocqui.ditusmartfoodbackend.dto.admin.category;

import lombok.Builder;

@Builder
public record CategoryAdminResponse(
        String id,
        String name,
        String description,
        String imageUrl
) {
}
