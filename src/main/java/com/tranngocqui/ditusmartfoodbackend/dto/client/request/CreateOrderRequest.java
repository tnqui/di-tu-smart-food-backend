package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderRequest {
    private UUID userId;

    private List<OrderItemRequest> items;

    private String shippingAddress;
    private String recipientName;
    private String recipientPhone;

    private String paymentMethod;  // ví dụ: COD, MOMO, VNPAY
    private String deliveryMethod;
}
