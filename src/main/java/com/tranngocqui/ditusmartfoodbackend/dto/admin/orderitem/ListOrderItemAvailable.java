package com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem;

import com.tranngocqui.ditusmartfoodbackend.dto.client.auth.response.OrderItemResponse;

import java.util.List;

public record ListOrderItemAvailable(
        List<OrderItemResponse> orderItemResponses,
        String message
) {
}
