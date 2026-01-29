package org.example.emailservice_nov2025evening.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.dtos.EmailDto;
import org.example.emailservice_nov2025evening.emailsender.EmailSender;
import org.example.emailservice_nov2025evening.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Component
public class KafkaEmailConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private EmailSender emailSender;

    @KafkaListener(topics="password_reset", groupId = "emailService")
      public void sendEmail(String message) {
          try {
              EmailDto emailDto = objectMapper.readValue(message, EmailDto.class);

              Properties props = new Properties();
              props.put("mail.smtp.host", "smtp.gmail.com");
              props.put("mail.smtp.port", "587");
              props.put("mail.smtp.auth", "true");
              props.put("mail.smtp.starttls.enable", "true");

              Session session = Session.getInstance(props);

              emailSender.send(
                      session,
                      emailDto.getTo(),
                      emailDto.getSubject(),
                      emailDto.getBody()
              );
          } catch (JsonProcessingException exception) {
              throw new RuntimeException(exception.getMessage());
          }
      }
}
