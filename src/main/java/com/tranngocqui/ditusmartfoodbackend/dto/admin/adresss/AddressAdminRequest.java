package com.tranngocqui.ditusmartfoodbackend.dto.admin.adresss;


import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record AddressAdminRequest(
        UUID userId,
        String houseNumber,
        String street,
        String ward,
        String district,
        String city,
        String country,
        Boolean isDefault
) {
    public String buildFullAddress() {
        return Stream.of(street, ward, district, city, country).filter(s -> s != null && !s.isBlank())
                .collect(Collectors.joining(", "));
    }

}
