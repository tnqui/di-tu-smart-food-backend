package com.tranngocqui.ditusmartfoodbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tranngocqui.ditusmartfoodbackend.configuration.SecurityConfig;
import com.tranngocqui.ditusmartfoodbackend.entity.CustomUserDetails;
import com.tranngocqui.ditusmartfoodbackend.service.CustomUserDetailsService;
import com.tranngocqui.ditusmartfoodbackend.service.application.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.NonNull;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        if (isPublicEndpoint(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractTokenFromRequest(request);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Jwt jwt = jwtDecoder.decode(token);

                if (!jwtService.validateAccessToken(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    return;
                }

                String username = jwt.getSubject();

                CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                customUserDetails,
                                null,
                                customUserDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                handleException(response, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String requestPath) {
        return Arrays.stream(SecurityConfig.PUBLIC_ENDPOINTS)
                .anyMatch(pattern -> pathMatcher.match(pattern, requestPath));
    }


    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void handleException(HttpServletResponse response,
                                 HttpStatus status,
                                 String message,
                                 String detail) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());
//        ApiResponse<AppException> apiResponse = ApiResponse.<AppException>builder()
//                .code(status.value())
//                .message(message)
//                .build();

        log.warn(detail);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
//        response.getWriter().write(mapper.writeValueAsString(apiResponse));
    }

//    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//        try {
//            String scope = jwt.getClaimAsString("scope");
//
//            if (scope != null && !scope.trim().isEmpty()) {
//                return Arrays.stream(scope.split(" "))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//            }
//        } catch (Exception e) {
//            logger.debug("Failed to extract authorities from JWT scope: " + e.getMessage());
//        }
//
//        return Collections.emptyList();
//    }

}