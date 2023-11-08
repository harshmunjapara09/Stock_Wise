package com.example.StockWise.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp)  {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("OTP Verification");

            StringBuilder body = new StringBuilder();

            body.append("Login Otp : ");
            body.append(otp);
            helper.setText(body.toString(), true);
            javaMailSender.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }

    }

}


