package com.tranngocqui.ditusmartfoodbackend.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String msg, Throwable cause) {
        super(msg, cause);
    }
}