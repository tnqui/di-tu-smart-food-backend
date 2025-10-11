package com.tranngocqui.ditusmartfoodbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranngocqui.ditusmartfoodbackend.dto.ApiResponse;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<AppException> apiResponse = ApiResponse.<AppException>builder()
                .code(ErrorCode.FORBIDDEN.getCode())
                .message(ErrorCode.FORBIDDEN.getMessage())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
