//package com.test.bakery.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class EmailService {
//
//    private JavaMailSender emailSender;
//
//    public EmailService(JavaMailSender emailSender) {
//        this.emailSender = emailSender;
//    }
//
//    public EmailService() {
//    }
//
//    public void sendSimpleMessage(
//            String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }
//}