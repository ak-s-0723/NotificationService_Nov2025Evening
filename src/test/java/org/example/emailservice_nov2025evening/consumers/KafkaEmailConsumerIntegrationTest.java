package org.example.emailservice_nov2025evening.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.emailservice_nov2025evening.dtos.EmailDto;
import org.example.emailservice_nov2025evening.emailsender.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(topics = "password_reset", partitions = 1)
class KafkaEmailConsumerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmailSender smtpEmailSender;

    @Test
    void shouldConsumeKafkaMessageViaKafkaListener() throws Exception {

        EmailDto dto = new EmailDto();
        dto.setTo("user@example.com");
        dto.setFrom("sender@example.com");
        dto.setSubject("Reset");
        dto.setBody("Reset link");

        String message = objectMapper.writeValueAsString(dto);

        kafkaTemplate.send("password_reset", message);

        await()
                .atMost(5, TimeUnit.SECONDS)
                .untilAsserted(() ->
                        verify(smtpEmailSender, times(1))
                                .send(any(), eq("user@example.com"), eq("Reset"), eq("Reset link"))
                );
    }
}