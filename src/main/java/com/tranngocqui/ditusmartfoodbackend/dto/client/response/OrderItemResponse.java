package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long id;
    private Integer quantity;
    private BigDecimal priceAtOrderTime;
    private String menuItemName;
}
