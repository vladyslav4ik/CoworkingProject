package coworking.project.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage message;

    public EmailServiceImpl(JavaMailSender javaMailSender, SimpleMailMessage message) {
        this.javaMailSender = javaMailSender;
        this.message = message;
    }

    public void sendSignUpEmail(String to) {
        message.setTo(to);
        message.setSubject("You are successfully registered on our site!");
        message.setText("Thank you for registration on our site!");
        javaMailSender.send(message);
    }

    public void sendSuccessfulPaymentMessage(String to) {
        message.setTo(to);
        message.setSubject("Information about payment");
        message.setText("You successfully paid for your reservation! Thank u.");
        javaMailSender.send(message);
    }

    public void sendSuccessfulConfirmedMessage(String to) {
        message.setTo(to);
        message.setSubject("Information about confirming your reservation");
        message.setText("Administrator just confirmed your reservation!");
        javaMailSender.send(message);
    }
}