package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private UUID id;

    private String userId;

    private List<OrderItemResponse> items;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private BigDecimal shippingFee;

    private Instant createdAt;

    private String shippingAddress;

    private String recipientName;

    private String recipientPhone;

    private String paymentMethodName;

//    private DeliveryMethod deliveryMethod;

}