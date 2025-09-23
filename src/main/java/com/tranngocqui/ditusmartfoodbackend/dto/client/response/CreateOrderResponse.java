package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tranngocqui.ditusmartfoodbackend.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime createdAt;

    private String shippingAddress;

    private String recipientName;

    private String recipientPhone;

    private String paymentMethodName;

//    private DeliveryMethod deliveryMethod;

}