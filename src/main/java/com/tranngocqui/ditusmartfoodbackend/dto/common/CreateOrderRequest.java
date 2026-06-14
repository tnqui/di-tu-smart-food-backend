package com.tranngocqui.ditusmartfoodbackend.dto.common;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.orderitem.OrderItemCreateRequest;
import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentMethodProvider;

import java.util.List;

public record CreateOrderRequest(
        String userId,
        PaymentMethodProvider paymentMethodCode,
        DeliveryMethodProvider deliveryMethodCode,
        List<OrderItemCreateRequest> items
) {
}
