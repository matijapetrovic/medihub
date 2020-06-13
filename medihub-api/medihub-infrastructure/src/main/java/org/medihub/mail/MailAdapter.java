package org.medihub.mail;

import lombok.RequiredArgsConstructor;
import org.medihub.application.ports.outgoing.mail.SendEmailPort;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MailAdapter implements SendEmailPort {
    private final JavaMailSender mailSender;
    private final Environment env;

    @Override
    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }
}
