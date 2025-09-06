package com.tranngocqui.ditusmartfoodbackend.dto.admin.auth.request;

import lombok.Data;

@Data
public class TwoFADisableRequest {
    private String email;
    private String code;
}
