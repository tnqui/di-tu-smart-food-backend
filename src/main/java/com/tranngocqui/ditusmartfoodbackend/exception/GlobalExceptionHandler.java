package com.tranngocqui.ditusmartfoodbackend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import software.amazon.awssdk.services.ses.model.MessageRejectedException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<?>> handleApiException(AppException e) {
        log.error(e.getMessage(), e);
        ErrorCode errorCode = e.getErrorCode();

        if (e.getDetail() == null) {
            return ResponseEntity.status(errorCode.getHttpStatus())
                    .body(ApiResponse.error(errorCode.getCode(), e.getMessage()));
        } else
            return ResponseEntity.status(errorCode.getHttpStatus())
                    .body(ApiResponse.error(errorCode.getCode(), e.getMessage(), e.getDetail()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(
            AuthorizationDeniedException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.error(403, "You don't have permission to perform this action"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String messageCode = Objects.requireNonNull(e.getBindingResult()
                        .getFieldError())
                .getDefaultMessage();
        ErrorCode errorCode = ErrorCode.parse(messageCode);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<ApiResponse<?>> handleRedisConnectionFailureException(RedisConnectionFailureException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Server Error"));
    }

    @ExceptionHandler(MessageRejectedException.class)
    public ResponseEntity<ApiResponse<?>> handleMessageRejectedException(MessageRejectedException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Email is not verified!"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Invalid Content-Type"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getParameterName() + " is missing"));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodValidation(
            HandlerMethodValidationException ex
    ) {

        List<Map<String, Object>> errors = new ArrayList<>();

        for (ParameterValidationResult result : ex.getParameterValidationResults()) {

            Map<String, Object> error = new HashMap<>();

            MethodParameter param = result.getMethodParameter();

            error.put("field", param.getParameterName());

            error.put("message",
                    result.getResolvableErrors()
                            .stream()
                            .map(MessageSourceResolvable::getDefaultMessage)
                            .toList()
            );

            errors.add(error);
        }

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR" + errors));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolation(
            ConstraintViolationException ex
    ) {

        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getConstraintViolations().forEach(v -> {

            Map<String, Object> error = new HashMap<>();

            error.put("field", v.getPropertyPath().toString());
            error.put("message", v.getMessage());
            error.put("invalidValue", v.getInvalidValue());

            errors.add(error);
        });

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR" + errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        Throwable cause = e.getCause();

        if (cause instanceof InvalidFormatException ex) {

            Class<?> targetType = ex.getTargetType();

            if (targetType.isEnum()) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error(
                                400,
                                "Invalid value '" + ex.getValue() +
                                        "' for enum " + targetType.getSimpleName()
                        ));
            }
        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(
                        400,
                        "Incorrect input data format"
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String paramName = e.getParameter().getParameterName();

        String message = Optional.ofNullable(e.getRequiredType())
                .filter(Class::isEnum)
                .map(type -> {
                    Object[] enumValues = type.getEnumConstants();
                    String validValues = Arrays.stream(enumValues)
                            .map(Object::toString)
                            .collect(Collectors.joining(", "));
                    return String.format(
                            "Parameter '%s' must be one of: [%s]. Received: %s",
                            paramName,
                            validValues,
                            e.getValue()
                    );
                })
                .orElseGet(() -> String.format(
                        "Parameter '%s' has invalid type. Received: %s",
                        paramName,
                        e.getValue()
                ));

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), message));
    }

}
