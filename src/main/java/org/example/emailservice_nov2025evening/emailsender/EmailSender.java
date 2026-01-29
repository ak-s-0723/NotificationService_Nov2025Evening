package org.example.emailservice_nov2025evening.emailsender;

import javax.mail.Session;

public interface EmailSender {
    void send(Session session, String to, String subject, String body);
}
