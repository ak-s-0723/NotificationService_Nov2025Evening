package org.example.emailservice_nov2025evening.emailsender;

import org.example.emailservice_nov2025evening.utils.EmailUtil;
import org.springframework.stereotype.Component;

import javax.mail.Session;

@Component
public class SmtpEmailSender implements EmailSender {
    @Override
    public void send(Session session, String to, String subject, String body) {
        EmailUtil.sendEmail(session, to, subject, body);
    }
}