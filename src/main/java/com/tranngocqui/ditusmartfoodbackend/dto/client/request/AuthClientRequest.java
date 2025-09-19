package com.tranngocqui.ditusmartfoodbackend.dto.client.request;

import lombok.Data;

@Data
public class AuthClientRequest {
    private String identifier;
    private String password;
}

