package com.tranngocqui.ditusmartfoodbackend.dto.admin.product;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductNameStockResponse(
        UUID productId,
        String productName,
        Integer stock
) {
}
