package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodResponse {
    private Long id;
    private String name;
    private String description;
}
