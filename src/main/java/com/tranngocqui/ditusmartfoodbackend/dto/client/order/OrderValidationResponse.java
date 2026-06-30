package com.tranngocqui.ditusmartfoodbackend.dto.client.order;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record OrderValidationResponse(
        String orderId,
        List<Item> validItems,
        List<Item> invalidItems,
        Integer numberOfItems,
        Integer accepted,
        Integer rejected
) {
    @Builder
    public record Item(
            UUID productId,
            String productName,
            Integer stock,
            Integer quantity,
            Boolean accepted,
            String reason
    ) {
    }
}
