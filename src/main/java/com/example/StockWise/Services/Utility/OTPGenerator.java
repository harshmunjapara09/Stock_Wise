package com.example.StockWise.Services.Utility;


import java.security.SecureRandom;
import java.util.Random;

public class OTPGenerator {
    private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 6;

    public static String generateOTP() {
        Random random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(OTP_CHARS.length());
            otp.append(OTP_CHARS.charAt(randomIndex));
        }

        return otp.toString();
    }
}