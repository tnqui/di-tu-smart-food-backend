package com.tranngocqui.ditusmartfoodbackend.dto.admin.order;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCreateRequest;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderAdminCreateRequest(
        String userId,
        @NotNull
        List<OrderItemCreateRequest> orderItems,
        BigDecimal shippingFee,
        String shippingAddress,
        Double latitude,
        Double longitude,
        String recipientName,
        String recipientPhone,
        String paymentMethodId,
        String deliveryMethodId
) {
}
