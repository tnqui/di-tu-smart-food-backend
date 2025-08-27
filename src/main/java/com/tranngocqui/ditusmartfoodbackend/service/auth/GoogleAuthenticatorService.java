package com.tranngocqui.ditusmartfoodbackend.service.auth;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class GoogleAuthenticatorService {

    private final GoogleAuthenticator googleAuthenticator;

    public GoogleAuthenticatorService() {
        this.googleAuthenticator = new GoogleAuthenticator();
    }

    /**
     * Tạo secret key mới cho user
     */
    public String generateSecretKey() {
        GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
        return key.getKey();
    }

    /**
     * Tạo QR Code URL cho Google Authenticator
     */
    public String generateQRCodeUrl(String username, String secretKey, String issuer) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(
                issuer,
                username,
                new GoogleAuthenticatorKey.Builder(secretKey).build()
        );
    }

    /**
     * Xác minh mã TOTP
     */
    public boolean verifyCode(String secretKey, int code) {
        return googleAuthenticator.authorize(secretKey, code);
    }

    /**
     * Tạo QR Code image dạng Base64
     */
    public String generateQRCodeImage(String qrCodeUrl) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeUrl, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo QR Code", e);
        }
    }
}