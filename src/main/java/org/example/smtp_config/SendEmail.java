package org.example.smtp_config;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendEmail {

    private final String smtpHost = "smtp-relay.brevo.com"; // SMTP сервер Brevo
    private final int smtpPort = 587; // TLS порт
    private final String username = "a48a90001@smtp-brevo.com"; // API email
    private final String password = "xn7LmgHZsfXGqcTY";        // API ключ
    private final String fromEmail = "kirillkasanov291@gmail.com";
    private final String fromName = "Kirill Kasyanov";

    private final Session session;

    // Конструктор создает сессию один раз
    public SendEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", String.valueOf(smtpPort));

        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    // Метод для отправки текста
    public void sendEmail(String toEmail, String subject, String textContent) {
        try {
            Message message = new MimeMessage(session);

            // От кого
            message.setFrom(new InternetAddress(fromEmail, fromName));

            // Кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Тема
            message.setSubject(subject);

            // Текст
            message.setText(textContent);

            // Отправка
            Transport.send(message);
            System.out.println("{'result': true, 'message': 'Email sent'}");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("{'result': false, 'message': '" + e.getMessage() + "'}");
        }
    }
}