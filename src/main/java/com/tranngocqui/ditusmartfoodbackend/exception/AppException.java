package com.tranngocqui.ditusmartfoodbackend.exception;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends RuntimeException {

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;

}
