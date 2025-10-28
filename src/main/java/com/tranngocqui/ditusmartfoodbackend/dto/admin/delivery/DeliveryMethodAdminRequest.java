package com.tranngocqui.ditusmartfoodbackend.dto.admin.delivery;

import com.tranngocqui.ditusmartfoodbackend.enums.DeliveryMethodProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMethodAdminRequest {
    private DeliveryMethodProvider shortName;

    private String fullName;

    private Integer time;

    private BigDecimal price;
}
