package org.medihub.application.ports.outgoing.mail;

public interface SendEmailPort {
    void sendEmail(String to, String subject, String text);
}