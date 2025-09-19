package com.tranngocqui.ditusmartfoodbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDtoResponse {
    private Long id;
    private String name;
}
