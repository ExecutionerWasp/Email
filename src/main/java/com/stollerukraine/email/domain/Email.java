package com.stollerukraine.email.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "email_messages")
@ToString
@EqualsAndHashCode(of = {"id", "from", "to", "sendingDate"})
public class Email implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty
    private long id;

    @Column(name = "TITLE")
    @JsonProperty
    private String title;
    @Column(name = "CONTENT")
    @JsonProperty
    private String content;

    @Column(name = "DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty
    private Date sendingDate;

    @Column(name = "EMAIL_FROM")
    @JsonProperty
    private String from;
    @Column(name = "EMAIL_TO")
    @JsonProperty
    private String to;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @JsonProperty
    private EmailStatus status;

    public Email(){
        sendingDate = new Date();
        status = EmailStatus.IN_PROCESS;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
