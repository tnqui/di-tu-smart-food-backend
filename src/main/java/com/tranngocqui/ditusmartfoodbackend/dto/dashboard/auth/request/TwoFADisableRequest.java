package com.tranngocqui.ditusmartfoodbackend.dto.dashboard.auth.request;

import lombok.Data;

@Data
public class TwoFADisableRequest {
    private String email;
    private String code;
}
