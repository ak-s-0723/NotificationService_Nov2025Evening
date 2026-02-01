package org.example.emailservice_nov2025evening.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.emailsender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaEmailConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmailSender emailSender;

      public void sendEmail(String message) {
      }
}
