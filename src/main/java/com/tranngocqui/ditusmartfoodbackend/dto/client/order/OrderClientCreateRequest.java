package com.tranngocqui.ditusmartfoodbackend.dto.client.order;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethod;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record OrderClientCreateRequest(
        @NotEmpty(message = "EMPTY_LIST_PRODUCT")
        List<OrderItemRequest> items,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String recipientPhone,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String recipientName,
        @NotNull(message = "REQUIRED_FIELD_MISSING")
        DeliveryMethod deliveryMethod,
        @NotNull(message = "REQUIRED_FIELD_MISSING")
        PaymentMethod paymentMethod,
        String note
) {
    public record OrderItemRequest(
            @NotNull(message = "REQUIRED_FIELD_MISSING")
            UUID productId,
            @NotNull(message = "REQUIRED_FIELD_MISSING")
            @Positive(message = "QUANTITY_MUST_BE_POSITIVE")
            Integer quantity
    ) {
    }
}
