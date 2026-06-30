package com.tranngocqui.ditusmartfoodbackend.test.record;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateOrderStep1Request(
        List<OrderItem> items,
        String note
) {
    @Builder
    public record OrderItem(
            UUID productId,
            Integer quantity
    ) {
        @Override
        public String toString() {
            return "OrderItem{" +
                    "productId=" + productId +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
