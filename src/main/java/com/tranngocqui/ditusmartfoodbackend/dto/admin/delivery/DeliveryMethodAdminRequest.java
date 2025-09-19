package com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMethodAdminRequest {
    private String name;

    private String description;

    private Integer time;

    private Double price;
}
