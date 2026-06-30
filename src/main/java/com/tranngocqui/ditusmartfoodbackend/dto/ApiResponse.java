package com.tranngocqui.ditusmartfoodbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;


@Builder
public record ApiResponse<T>(
        int code,
        boolean success,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String detail,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data,
        Instant timestamp
) {
    public static <T> ApiResponse<T> ok() {
        return ApiResponse.<T>builder()
                .code(200)
                .success(true)
                .message("Success")
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .success(true)
                .message("Success")
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return ApiResponse.<T>builder()
                .code(200)
                .success(true)
                .message(message)
                .data(data)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> ok(String message) {
        return ApiResponse.<T>builder()
                .code(200)
                .success(true)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return ApiResponse.<T>builder()
                .code(status)
                .success(false)
                .message(message)
                .timestamp(Instant.now())
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message, String detail) {
        return ApiResponse.<T>builder()
                .code(status)
                .success(false)
                .message(message)
                .detail(detail)
                .timestamp(Instant.now())
                .build();
    }

}
