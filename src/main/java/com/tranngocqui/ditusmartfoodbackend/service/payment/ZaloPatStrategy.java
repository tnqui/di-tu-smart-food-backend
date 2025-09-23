package com.tranngocqui.ditusmartfoodbackend.service.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.properties.ZaloPayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ZaloPatStrategy implements PaymentStrategy {

    private final ZaloPayProperties zaloPayProperties;

    @Override
    public PaymentProvider getProvider() {
        return PaymentProvider.ZALOPAY;
    }

    @Override
    public PaymentResponse pay(Order order) throws Exception {
        long transID = (long) (Math.random() * 1_000_000);
        String appTransID = java.time.format.DateTimeFormatter.ofPattern("yyMMdd")
                .format(java.time.LocalDate.now()) + "_" + transID;

        String embedDataJson = "{\"zlppaymentid\":\"P271021\"}";
        String itemsJson = "[]"; // TODO: convert cart items to JSON

        long amount = 50_000L;
        long appTime = System.currentTimeMillis();
        String description = "Payment for the order #" + transID;
        String bankCode = "zalopayapp";

        // Tạo data để hash MAC
        String data = String.join("|",
                zaloPayProperties.appId(),
                appTransID,
                "user123",
                String.valueOf(amount),
                String.valueOf(appTime),
                embedDataJson,
                itemsJson
        );

        String mac = hmacSHA256(data, zaloPayProperties.key1());

        // Build request params
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(zaloPayProperties.endpoint() + "create")
                .queryParam("app_id", zaloPayProperties.appId())
                .queryParam("app_trans_id", appTransID)
                .queryParam("app_user", "user123")
                .queryParam("app_time", appTime)
                .queryParam("item", itemsJson)
                .queryParam("embed_data", embedDataJson)
                .queryParam("amount", amount)
                .queryParam("description", description)
                .queryParam("bank_code", bankCode)
                .queryParam("callback_url", zaloPayProperties.callbackUrl())
                .queryParam("mac", mac);

        // Gửi request
        WebClient client = WebClient.create();
        String result = client.post()
                .uri(builder.toUriString())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Parse JSON (thường trả về order_url)
        ObjectMapper mapper = new ObjectMapper();
        return PaymentResponse.builder()
                .paymentUrl(mapper.readTree(result).get("order_url").asText())
                .build();
    }

    private String hmacSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes());
        return bytesToHex(hash);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
