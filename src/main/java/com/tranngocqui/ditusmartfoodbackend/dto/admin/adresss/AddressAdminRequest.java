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
public class AddressAdminRequest {
    private String label;
    private String fullAddress;
    private boolean defaultAddress;
    private UUID userId;
}
