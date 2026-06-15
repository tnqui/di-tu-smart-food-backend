package com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AddressAdminResponse(
        String id,
        String label,
        String fullAddress,
        Double latitude,
        Double longitude,
        boolean defaultAddress,
        UUID userId
) {}
