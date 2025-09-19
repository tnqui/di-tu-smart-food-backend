package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {
    private Long menuItemId;
    private Integer quantity;
    private BigDecimal priceAtOrderTime;
}
