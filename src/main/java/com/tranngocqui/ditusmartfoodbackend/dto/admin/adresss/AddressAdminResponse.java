package com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressAdminResponse {
    private Long id;
    private String label;
    private String fullAddress;
    private Double latitude;
    private Double longitude;
    private boolean defaultAddress;
    private UUID userId;
}
