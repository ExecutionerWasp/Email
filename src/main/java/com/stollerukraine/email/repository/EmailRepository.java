package com.stollerukraine.email.repository;

import com.stollerukraine.email.domain.EmailStatus;
import com.stollerukraine.email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByFrom(String from);

    List<Email> findByTo(String to);

    List<Email> findBySendingDate(Date sendingDate);

    List<Email> findByFromAndToAndSendingDate(String from, String to, Date sendingDate);

    List<Email> findByTitle(String title);

    List<Email> findByStatus(EmailStatus status);
}
