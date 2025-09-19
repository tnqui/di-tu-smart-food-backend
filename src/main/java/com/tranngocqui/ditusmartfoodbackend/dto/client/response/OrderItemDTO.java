package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long menuItem;
    private String name;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
}
