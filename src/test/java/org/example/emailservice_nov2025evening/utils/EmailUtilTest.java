package org.example.emailservice_nov2025evening.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

import static org.mockito.Mockito.*;

class EmailUtilTest {

    @Test
    void shouldSendEmailSuccessfully() {

        // Arrange
        Properties props = new Properties();
        Session session = Session.getInstance(props);

        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {

            // Act
            EmailUtil.sendEmail(
                    session,
                    "test@example.com",
                    "Test Subject",
                    "Hello World"
            );

            // Assert
            mockedTransport.verify(() -> Transport.send(any()), times(1));
        }
    }
}