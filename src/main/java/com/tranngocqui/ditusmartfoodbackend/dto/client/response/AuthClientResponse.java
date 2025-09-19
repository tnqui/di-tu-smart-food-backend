package com.tranngocqui.ditusmartfoodbackend.dto.client.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import java.time.Instant;
import java.util.List;

@Data
public class AuthClientResponse {
    private UUID userId;
    private String email;
    private String fullName;
    private String phone;
    private String avatarUrl;
    private List<AddressClientResponse> addresses;

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
//
//    private int loyaltyPoints;
//    private String preferredPaymentMethod; // ví dụ: "CASH", "CARD"

    // ==== Notification settings (optional) ====
//    private boolean notificationEnabled;

}
