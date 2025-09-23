package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

import com.tranngocqui.ditusmartfoodbackend.dto.client.response.OrderItemResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class CreateOrderRequest {

    private String userId;

    private List<OrderItemRequest> items;

    private Long deliveryMethodId;

    private String shippingAddress;

    private String recipientName;

    private String recipientPhone;

    private Long paymentMethodId;

}
