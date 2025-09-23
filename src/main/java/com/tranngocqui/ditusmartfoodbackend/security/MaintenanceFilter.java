package com.tranngocqui.ditusmartfoodbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Setter
@Component
public class MaintenanceFilter extends OncePerRequestFilter {
    private volatile boolean serverReady = false;

    @EventListener
    public void handleApplicationReady(ApplicationReadyEvent event) {
        this.serverReady = true;
        log.info("Server is ready to accept requests");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!serverReady) {
            log.debug("Request rejected - server not ready: {} {}", request.getMethod(), request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.setHeader("Retry-After", "5");
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"code\":503, \"message\":\"Server đang khởi động, vui lòng thử lại sau.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}