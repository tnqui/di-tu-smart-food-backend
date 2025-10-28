package com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem;

import jakarta.validation.constraints.NotNull;

public record OrderItemCreateRequest(
        @NotNull
        String itemId,
        @NotNull
        int quantity,
        String note
) {
}
