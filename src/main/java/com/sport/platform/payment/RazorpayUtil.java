package com.sport.platform.payment;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class RazorpayUtil {

    public static boolean verifySignature(String orderId,
                                          String paymentId,
                                          String signature,
                                          String secret) {

        try {

            String data = orderId + "|" + paymentId;

            Mac mac = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

            mac.init(secretKey);

            byte[] hash = mac.doFinal(data.getBytes());

            String generatedSignature = bytesToHex(hash);

            return generatedSignature.equals(signature);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String bytesToHex(byte[] bytes) {

        StringBuilder hex = new StringBuilder();

        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }
}