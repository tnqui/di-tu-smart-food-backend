package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientDTO {
    private String name;
    private String phone;
    private String address;
}
