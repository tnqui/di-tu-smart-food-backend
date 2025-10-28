package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private String userId;

    private List<OrderItemRequest> items;

    private String deliveryMethodId;

    private String shippingAddress;

    private String recipientName;

    private String recipientPhone;

    private String paymentMethodId;

}
