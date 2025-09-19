package com.tranngocqui.ditusmartfoodbackend.controller.external;

import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.ErrorCode;
import com.tranngocqui.ditusmartfoodbackend.enums.RoleEnums;
import com.tranngocqui.ditusmartfoodbackend.exception.AppException;
import com.tranngocqui.ditusmartfoodbackend.service.auth.AuthenticationService;
import com.tranngocqui.ditusmartfoodbackend.service.jwt.JwtServiceImpl;
import com.tranngocqui.ditusmartfoodbackend.service.role.RoleService;
import com.tranngocqui.ditusmartfoodbackend.service.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class GoogleAuthController {

    private final AuthenticationService authenticationService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtServiceImpl jwtService;
    private final UserService userService;
    private final RoleService roleService;


    @GetMapping("/auth/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String url = "https://accounts.google.com/o/oauth2/auth" +
                "?client_id=683974456296-lovaf6ssf6qq9thimn8r2m3090p9p3jo.apps.googleusercontent.com" +
                "&redirect_uri=http://localhost:8080/oauth2/callback/google" +
                "&response_type=code" +
                "&scope=openid%20profile%20email";
        response.sendRedirect(url);
    }

    @GetMapping("/oauth2/callback/google")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        // 1. Lấy access token từ Google
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "683974456296-lovaf6ssf6qq9thimn8r2m3090p9p3jo.apps.googleusercontent.com");
        params.put("client_secret", "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");
        params.put("redirect_uri", "http://localhost:8080/oauth2/callback/google");
        params.put("grant_type", "authorization_code");
        params.put("code", code);

        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token", params, Map.class);

        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // 2. Lấy thông tin user từ Google
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                entity,
                Map.class);

        Map<String, Object> userInfo = userResponse.getBody();
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");

        // 3. Tìm user trong DB hoặc tạo mới
        User user = userService.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setFullName(name);
                    newUser.setAvatarUrl(picture);
                    Role userRole = roleService.findByName(RoleEnums.CUSTOMER.name())
                            .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
                    newUser.setRoles(new HashSet<>(Collections.singleton(userRole)));
                    return userService.save(newUser);
                });

        // 4. Sinh JWT bằng JwtService
        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(Map.of(
                "token", jwt,
                "user", Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "name", user.getFullName(),
                        "avatar", user.getAvatarUrl()
                )
        ));
    }
}
