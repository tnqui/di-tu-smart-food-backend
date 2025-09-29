package com.tranngocqui.ditusmartfoodbackend.service.paymenttransaction.momo;

import com.tranngocqui.ditusmartfoodbackend.entity.Order;
import com.tranngocqui.ditusmartfoodbackend.service.order.OrderService;
import com.tranngocqui.ditusmartfoodbackend.ultis.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class MoMoService {

    private final String partnerCode;
    private final String accessKey;
    private final String secretKey;
    private final String callbackUrl;
    private final String redirectUrl;
    private final String requestType;
    private final String momoUrl;


    public MoMoService(
            @Value("${momo.partner-code}") String partnerCode,
            @Value("${momo.access-key}") String accessKey,
            @Value("${momo.secret-key}") String secretKey,
            @Value("${momo.callback-url}") String callbackUrl,
            @Value("${momo.redirect-url}") String redirectUrl,
            @Value("${momo.request-type}") String requestType,
            @Value("${momo.momo-url}") String momoUrl,
            OrderService orderService) {
        this.partnerCode = partnerCode;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.callbackUrl = callbackUrl;
        this.redirectUrl = redirectUrl;
        this.requestType = requestType;
        this.momoUrl = momoUrl;
    }

    public String createPaymentRequest(Order order) throws Exception {
        String orderId = String.valueOf(order.getId());
        String amount = order.getTotalAmount().setScale(0, RoundingMode.UP).toPlainString();
        String requestId = partnerCode + new Date().getTime();
        String orderInfo = "SN Mobile";
        String extraData = "";

        String rawSignature = String.format(
                "accessKey=%s&amount=%s&extraData=%s&ipnUrl=%s&orderId=%s&orderInfo=%s&partnerCode=%s&redirectUrl=%s&requestId=%s&requestType=%s",
                accessKey, amount, extraData, callbackUrl, orderId, orderInfo, partnerCode, redirectUrl,
                requestId, requestType);

        String signature = SignatureUtil.hmacSHA256(rawSignature, secretKey);

        JSONObject requestBody = new JSONObject();
        requestBody.put("partnerCode", partnerCode);
        requestBody.put("accessKey", accessKey);
        requestBody.put("requestId", requestId);
        requestBody.put("amount", amount);
        requestBody.put("orderId", orderId);
        requestBody.put("orderInfo", orderInfo);
        requestBody.put("redirectUrl", redirectUrl);
        requestBody.put("ipnUrl", callbackUrl);
        requestBody.put("extraData", extraData);
        requestBody.put("requestType", requestType);
        requestBody.put("signature", signature);
        requestBody.put("lang", "en");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(momoUrl);
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


    private String checkPaymentStatus(String orderId) {
        try {
            // Generate requestId
            String requestId = partnerCode + new Date().getTime();

            // Generate raw signature for the status check
            String rawSignature = String.format(
                    "accessKey=%s&orderId=%s&partnerCode=%s&requestId=%s",
                    accessKey, orderId, partnerCode, requestId);

            // Sign with HMAC SHA256
            String signature = SignatureUtil.hmacSHA256(rawSignature, secretKey);
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
            log.error("Error while checking Payment Status", e);
            return "{\"error\": \"Failed to check payment status: " + e.getMessage() + "\"}";
        }
    }

}
