package com.tranngocqui.ditusmartfoodbackend.ultis;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class SignatureUtil {
    public static String hmacSHA256(String data, String key) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256Hmac.init(secretKeySpec);
        return Hex.encodeHexString(sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }
}
