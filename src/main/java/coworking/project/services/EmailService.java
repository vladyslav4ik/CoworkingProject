package coworking.project.services;

public interface EmailService {
    void sendSignUpEmail(String to);
    void sendSuccessfulPaymentMessage(String to);
    void sendSuccessfulConfirmedMessage(String to);
}
