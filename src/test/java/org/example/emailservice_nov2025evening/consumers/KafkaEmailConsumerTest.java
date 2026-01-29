package org.example.emailservice_nov2025evening.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.utils.EmailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.mail.Session;

import static org.mockito.Mockito.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.utils.EmailUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.mail.Session;

import static org.mockito.Mockito.*;

class KafkaEmailConsumerTest {

    private KafkaEmailConsumer consumer;

    @BeforeEach
    void setup() {
        consumer = new KafkaEmailConsumer();
        consumer.objectMapper = new ObjectMapper();
    }

    @Test
    void shouldConsumeKafkaMessageAndSendEmail() throws Exception {

        // Arrange
        String message = """
            {
              "to": "user@example.com",
              "from": "sender@example.com",
              "subject": "Reset Password",
              "body": "Click link"
            }
        """;

        try (MockedStatic<EmailUtil> emailUtilMock = mockStatic(EmailUtil.class)) {

            // Act
            consumer.sendEmail(message);

            // Assert
            emailUtilMock.verify(() ->
                    EmailUtil.sendEmail(
                            any(Session.class),
                            eq("user@example.com"),
                            eq("Reset Password"),
                            eq("Click link")
                    ), times(1));
        }
    }
}