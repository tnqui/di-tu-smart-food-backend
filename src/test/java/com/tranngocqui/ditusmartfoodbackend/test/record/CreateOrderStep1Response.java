package com.tranngocqui.ditusmartfoodbackend.test.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateOrderStep1Response(
        String orderId,
        List<OrderItem> validItems,
        List<OrderItem> invalidItems,
        Instant expired
) {
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record OrderItem(
            UUID productId,
            Integer accepted,
            Integer rejected,
            String reason
    ) {
    }
}
