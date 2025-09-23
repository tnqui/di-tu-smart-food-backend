package com.tranngocqui.ditusmartfoodbackend.service.payment;

import com.tranngocqui.ditusmartfoodbackend.dto.payment.PaymentResponse;
import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.enums.PaymentProvider;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class MoMoPaymentStrategy implements PaymentStrategy {

    private final String partnerCode;
    private final String accessKey;
    private final String secretKey;
    private final OrderService orderService;

    public MoMoPaymentStrategy(
            @Value("${momo.partner-code}") String partnerCode,
            @Value("${momo.access-key}") String accessKey,
            @Value("${momo.secret-key}") String secretKey,
            OrderService orderService) {
        this.partnerCode = partnerCode;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.orderService = orderService;
    }

    private static final String REDIRECT_URL = "https://momo.vn/return";
    private static final String IPN_URL = "https://callback.url/notify";
    private static final String REQUEST_TYPE = "captureWallet";

    @Override
    public PaymentProvider getProvider() {
        return PaymentProvider.MOMO;
    }

    @Override
    public PaymentResponse pay(Order order) throws Exception {
        String payUrl = createPaymentRequest(order);

        return PaymentResponse.builder()
                .provider(PaymentProvider.MOMO)
                .paymentUrl(payUrl)
                .build();
    }

    private String createPaymentRequest(Order order) throws Exception {
        String orderId = String.valueOf(order.getId());
        String amount = order.getTotalAmount().setScale(0, RoundingMode.UP).toPlainString();
        String requestId = partnerCode + new Date().getTime();
        String orderInfo = "SN Mobile";
        String extraData = "";

        String rawSignature = String.format(
                "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                accessKey, amount, extraData, IPN_URL, orderId, orderInfo, partnerCode, REDIRECT_URL,
                requestId, REQUEST_TYPE);

        String signature = signHmacSHA256(rawSignature, secretKey);

        JSONObject requestBody = new JSONObject();
        requestBody.put("partnerCode", partnerCode);
        requestBody.put("accessKey", accessKey);
        requestBody.put("requestId", requestId);
        requestBody.put("amount", amount);
        requestBody.put("orderId", orderId);
        requestBody.put("orderInfo", orderInfo);
        requestBody.put("redirectUrl", REDIRECT_URL);
        requestBody.put("ipnUrl", IPN_URL);
        requestBody.put("extraData", extraData);
        requestBody.put("requestType", REQUEST_TYPE);
        requestBody.put("signature", signature);
        requestBody.put("lang", "en");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://test-payment.momo.vn/v2/gateway/api/create");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody.toString(), StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) result.append(line);

                String jsonString = result.toString();

                JSONObject jsonResponse = (JSONObject) JSONValue.parse(jsonString);

                return jsonResponse.getAsString("payUrl");
            }
        }
    }


    // HMAC SHA256 signing method
    private static String signHmacSHA256(String data, String key) throws Exception {
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSHA256.init(secretKey);
        byte[] hash = hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String checkPaymentStatus(String orderId) {
        try {
            // Generate requestId
            String requestId = partnerCode + new Date().getTime();

            // Generate raw signature for the status check
            String rawSignature = String.format(
                    "accessKey=%s&orderId=%s&partnerCode=%s&requestId=%s",
                    accessKey, orderId, partnerCode, requestId);

            // Sign with HMAC SHA256
            String signature = signHmacSHA256(rawSignature, secretKey);
            System.out.println("Generated Signature for Status Check: " + signature);

            // Prepare request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("partnerCode", partnerCode);
            requestBody.put("accessKey", accessKey);
            requestBody.put("requestId", requestId);
            requestBody.put("orderId", orderId);
            requestBody.put("signature", signature);
            requestBody.put("lang", "en");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://test-payment.momo.vn/v2/gateway/api/query");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody.toString(), StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                System.out.println("Response from MoMo (Status Check): " + result.toString());
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to check payment status: " + e.getMessage() + "\"}";
        }
    }
}
