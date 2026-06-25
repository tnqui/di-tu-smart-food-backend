package com.tranngocqui.ditusmartfoodbackend.constant;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

public class PublicUrl {
    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/**",
//            "/test/**",
//            "/api/payment/momo/**",
//            "/api/orders/**",
//            "/api/test/**",
//            "/api/auth/**",
//            "/api/auth/login",
//            "/api/auth/dashboard-login"
            //            "/api/auth/2fa-setup",
//            "/api/auth/2fa-confirm",
//            "/api/auth/verify-2fa",
//            "/api/admin/auth/login",
//            "/api/admin/auth/verify-2fa",
//            "/api/admin/auth/introspect",
//            "/api/admin/auth/register",
//            "/api/admin/auth/logout",
//            "/api/admin/auth/2fa/setup",
//            "/api/admin/auth/2fa/confirm",
//            "/api/admin/auth/2fa/confirm",
//            "/api/admin/auth/2fa/disable",
//            "/api/auth/token",
//            "/api/auth/login",
//            "/api/auth/register",
//            "/api/auth/refresh",
//            "/api/delivery-methods",
//            "/api/categories/**",
//            "/api/menu-items/**",
//            "/api/delivery-methods/**",
//            "/api/dishes/**",
//            "/actuator/health",
//            "/v3/api-docs/**",
//            "/swagger-ui/**"

    };

    public static boolean isPublicUrl(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getServletPath();

        for (String pattern : PublicUrl.PUBLIC_ENDPOINTS) {
            if (pathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }
}

