package coworking.project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoworkingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoworkingProjectApplication.class, args);
    }

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        return new JavaMailSenderImpl();
//    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("coworking.email.test@gmail.com");
        return simpleMailMessage;
    }
}
