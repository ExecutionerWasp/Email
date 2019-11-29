package com.stollerukraine.email.service.impl;

import com.stollerukraine.email.domain.EmailStatus;
import com.stollerukraine.email.domain.Email;
import com.stollerukraine.email.exception.EmailNotFoundException;
import com.stollerukraine.email.repository.EmailRepository;
import com.stollerukraine.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Value("${email.host}")
    private String host;

    @Value("${email.password}")
    private String password;

    @Override
    public Email save(Email email) {
        Objects.requireNonNull(email);
        email.setStatus(EmailStatus.SANDED);
        log.info("Saving email : " + email);
        return emailRepository.save(email);
    }

    @Override
    public HashSet<String> findAllEmailAddresses() {
        log.info("Finding all addresses");
        List<Email> all = emailRepository.findAll();
        return (HashSet<String>) all.stream().map(Email::getTo).collect(Collectors.toSet());
    }

    @Override
    public Email findById(long id) {
        log.info("Searching email by id : " + id);
        return emailRepository.findById(id).orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public Email deleteById(long id) {
        log.info("Deleting email with id : " + id);
        Email email = emailRepository.findById(id).orElseThrow(EmailNotFoundException::new);
        emailRepository.deleteById(id);
        return email;
    }

    @Override
    public Email send(Email email) {
        log.info("Sending email ...");
        log.info("from : " + email.getFrom());
        log.info("to : " + email.getTo());
        log.info("title : " + email.getTitle());
        log.info("content : " + email.getContent());

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(host, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getFrom()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo())
            );
            message.setSubject(email.getTitle());
            message.setText(email.getContent());

            Transport.send(message);
            email.setStatus(EmailStatus.SANDED);
            save(email);
            log.info("Email sending success");
        } catch (MessagingException e) {
            email.setStatus(EmailStatus.FAILED);
            log.warn("Email sending failed");
            log.warn(e);
        }
        return email;
    }

    @Override
    public List<Email> findByFrom(String from) {
        log.info("Searching emails from : " + from);
        return emailRepository.findByFrom(from);
    }

    @Override
    public List<Email> findByTo(String to) {
        log.info("Searching emails to : " + to);
        return emailRepository.findByTo(to);
    }

    @Override
    public List<Email> findBySendingDate(Date sendingDate) {
        log.info("Searching emails by sending date : " + sendingDate);
        return emailRepository.findBySendingDate(sendingDate);
    }

    @Override
    public List<Email> findByFromAndToAndSendingDate(String from, String to, Date sendingDate) {
        log.info("Searching emails by : ");
        log.info("From : " + from);
        log.info("To : " + to);
        log.info("Sending date : " + sendingDate);
        return emailRepository.findByFromAndToAndSendingDate(from, to, sendingDate);
    }

    @Override
    public List<Email> findByTitle(String title) {
        log.info("Searching emails by title : " + title);
        return emailRepository.findByTitle(title);
    }

    @Override
    public List<Email> findByStatus(EmailStatus status) {
        log.info("Searching email by status : " + status.name());
        return emailRepository.findByStatus(status);
    }
}
