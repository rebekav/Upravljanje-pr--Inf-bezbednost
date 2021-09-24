package com.fax.lekari.service.impl;

import com.fax.lekari.service.EmailService;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void zakazanPregledEmail(String klinika, String nazivPregleda, String vreme, String lekar, String emailTo) {
        VelocityContext context = new VelocityContext();
        context.put("klinika", klinika);
        context.put("nazivPregleda", nazivPregleda);
        context.put("vreme", vreme);
        context.put("lekar", lekar);

    }

    @Override
    public void sendPasswordless(String email, String token) {


        System.out.println(email);
        System.out.println("http://localhost:3000/auth/"+token);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@klicickicentar.com");
        message.setTo(email);
        message.setSubject("Passwordless Login");
        message.setText("http://localhost:3000/auth/"+token);
        emailSender.send(message);
    }
}
