package coworking.project.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSignUpEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("coworking.email.test@gmail.com");
        message.setTo(to);
        message.setSubject("You are successfully registered on our site!");
        message.setText("Thank you for registration on our site!");
        javaMailSender.send(message);
    }

    public void sendSuccessfulPaymentMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("coworking.email.test@gmail.com");
        message.setTo(to);
        message.setSubject("Information about payment");
        message.setText("You successfully paid for your reservation! Thank u.");
        javaMailSender.send(message);
    }

    public void sendSuccessfulConfirmedMessage(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("coworking.email.test@gmail.com");
        message.setTo(to);
        message.setSubject("Information about confirming your reservation");
        message.setText("Administrator just confirmed your reservation!");
        javaMailSender.send(message);
    }
}