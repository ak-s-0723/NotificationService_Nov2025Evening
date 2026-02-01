package org.example.emailservice_nov2025evening.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.emailsender.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.mail.Session;
import static org.mockito.Mockito.*;

class KafkaEmailConsumerTest {

    private KafkaEmailConsumer consumer;

    private EmailSender emailSender;

    @BeforeEach
    void setup() {
        consumer = new KafkaEmailConsumer();
        consumer.objectMapper = new ObjectMapper();
        emailSender = mock(EmailSender.class);
        consumer.emailSender = emailSender;
    }

    @Test
    void shouldConsumeKafkaMessageAndSendEmail() {
        String message = """
            {
              "to": "user@example.com",
              "from": "sender@example.com",
              "subject": "Reset Password",
              "body": "Click link"
            }
        """;

        consumer.sendEmail(message);

        verify(emailSender, times(1)).send(
                any(Session.class),
                eq("user@example.com"),
                eq("Reset Password"),
                eq("Click link")
        );
    }
}