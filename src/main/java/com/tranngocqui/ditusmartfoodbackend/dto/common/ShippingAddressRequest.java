package com.tranngocqui.ditusmartfoodbackend.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ShippingAddressRequest(
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String houseNumber,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String street,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String ward,
        @NotBlank(message = "REQUIRED_FIELD_MISSING")
        String city
) {
}
