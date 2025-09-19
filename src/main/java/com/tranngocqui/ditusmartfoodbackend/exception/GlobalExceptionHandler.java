package com.tranngocqui.ditusmartfoodbackend.exception;

import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${app.debug:false}")
    private boolean debugMode;


    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", ex.getStatus().value());
        body.put("error", ex.getStatus().getReasonPhrase());
        body.put("message", ex.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleRuntimeException(Exception ex) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());

        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Có lỗi xảy ra: dữ liệu không tồn tại");
    }


    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException ex) {

        ErrorCode errorCode = ex.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());

        apiResponse.setMessage(errorCode.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String enumKey = ex.getFieldErrors().getFirst().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_MESSAGE_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.SOMETHING_WENT_WRONG.getCode());
        apiResponse.setMessage(ErrorCode.SOMETHING_WENT_WRONG.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    ResponseEntity<ApiResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.NOT_FOUND.getCode());

        apiResponse.setMessage(ErrorCode.NOT_FOUND.getMessage());

        if (debugMode) {
            logger.error("Exception caught", ex);
        }

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<ApiResponse> handleAll(Throwable ex) {
        logger.error("Unexpected error", ex);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.SOMETHING_WENT_WRONG.getCode());
        apiResponse.setMessage(ErrorCode.SOMETHING_WENT_WRONG.getMessage());
        return ResponseEntity.status(500).body(apiResponse);
    }

}
