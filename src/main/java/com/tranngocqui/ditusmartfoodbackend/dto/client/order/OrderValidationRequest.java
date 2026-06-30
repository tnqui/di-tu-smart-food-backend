package com.tranngocqui.ditusmartfoodbackend.dto.client.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record OrderValidationRequest(
        @NotEmpty(message = "REQUIRED_FIELD_MISSING")
        @Size(max = 50, message = "LIST_TOO_LARGE")
        @Valid
        List<OrderItemRequest> requestItems
) {
    @Builder
    public record OrderItemRequest(
            @NotNull(message = "REQUIRED_FIELD_MISSING")
            UUID productId,
            @NotNull(message = "REQUIRED_FIELD_MISSING")
            @Min(value = 1, message = "VALUE_OUT_OF_RANGE")
            @Max(value = 20, message = "VALUE_OUT_OF_RANGE")
            Integer quantity
    ) {
    }
}
