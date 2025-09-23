package com.tranngocqui.ditusmartfoodbackend.exception;

import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;

public class UnsupportedPaymentException extends RuntimeException {
    public UnsupportedPaymentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
