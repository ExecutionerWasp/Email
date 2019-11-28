package com.stollerukraine.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;

@ResponseStatus(value = HttpStatus.LOCKED)
public class EmailSendingException extends MessagingException {
}
