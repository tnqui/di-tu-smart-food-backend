package com.tranngocqui.ditusmartfoodbackend.exception;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
