package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressClientResponse {
    private String id;
    private String label;
    private String fullAddress;
    private Double latitude;
    private Double longitude;
    private boolean defaultAddress;
}
