package com.sport.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCredentials(String toEmail, String username, String password) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Welcome to Sports Academy");

        message.setText(
                "Hello,\n\n" +
                "Your registration is successful.\n\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n\n" +
                "Login here:\n" +
                "https://my-app-frontend-empixfit.vercel.app/login\n\n" +
                "Best Regards,\nEmpixFitTeam"
        );

        mailSender.send(message);
    }
}