package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery.DeliveryMethodAdminResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private Long orderId;
    private String status;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal shippingFee;
    private String paymentMethod;
    private Long deliveryMethod;
    private RecipientDTO recipient;
    private List<OrderItemDTO> items;
}

