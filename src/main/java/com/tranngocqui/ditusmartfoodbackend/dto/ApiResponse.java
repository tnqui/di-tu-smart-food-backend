package com.tranngocqui.ditusmartfoodbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Builder.Default
    private int code = 1000;
    private String message;
    private T result;
}
