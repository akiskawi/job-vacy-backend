package com.manpower.backendProject.emailSender;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender mailSender;

    public void sendEmail(String to,
                          String sub,
                          String body){
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("akiskawi@gmail.com");
        message.setTo(to);
        message.setText(body);
        message.setSubject(sub);
        mailSender.send(message);

        System.out.println("Mail sent successfully ...");
    }
}
