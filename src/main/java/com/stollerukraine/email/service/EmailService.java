package com.stollerukraine.email.service;

import com.stollerukraine.email.domain.Email;
import com.stollerukraine.email.domain.EmailStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface EmailService {

    Email save(Email email);

    HashSet<String> findAllEmailAddresses();

    Email findById(long id);

    Email deleteById(long id);

    Email send(Email email);

    default Email deleteById(String id) {
        return deleteById(Long.parseLong(id));
    }

    default Email findById(String id){
        return findById(Long.parseLong(id));
    }

    List<Email> findByFrom(String from);

    List<Email> findByTo(String to);

    List<Email> findBySendingDate(Date sendingDate);

    List<Email> findByFromAndToAndSendingDate(String from, String to, Date sendingDate);

    List<Email> findByTitle(String title);

    List<Email> findByStatus(EmailStatus status);
}
