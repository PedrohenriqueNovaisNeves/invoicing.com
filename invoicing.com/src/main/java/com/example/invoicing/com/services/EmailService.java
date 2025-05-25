package com.example.invoicing.com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmailSimples(String para, String assunto, String corpo){

        SimpleMailMessage mesage = new SimpleMailMessage();

        mesage.setTo(para);
        mesage.setSubject(assunto);
        mesage.setText(corpo);
        mesage.setFrom("pp5709353@gmail.com");

        javaMailSender.send(mesage);
    }
}
