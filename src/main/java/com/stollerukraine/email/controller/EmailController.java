package com.stollerukraine.email.controller;

import com.stollerukraine.email.domain.Email;
import com.stollerukraine.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/sand")
    public ResponseEntity<Email> send(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String title,
            @RequestParam String content
    ){
        log.info("/email/sand");
        log.info("? from = "+from);
        log.info("& to = "+to);
        log.info("& title = "+title);
        log.info("& content = "+content);
        Email e = new Email();
        e.setFrom(from);
        e.setTo(to);
        e.setTitle(title);
        e.setContent(content);
        return ResponseEntity.ok(emailService.send(e));
    }

    @GetMapping("/sandedFrom")
    public List<Email> all(@RequestParam String from) {
        log.info("/sanded");
        log.info("? from = " + from);
        log.info(emailService.findByFrom(from));
        return emailService.findByFrom(from);
    }

    @GetMapping("/sandedTo")
    public List<Email> allTo(@RequestParam String to) {
        log.info("/sandedTo");
        log.info("to : " + to);
        return emailService.findByTo(to);
    }

    @GetMapping("/addresses")
    public List<String> addresses(){
        log.info("/addresses");
        return new ArrayList<>(emailService.findAllEmailAddresses());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Email> delete(@RequestParam long id) {
        log.info("/delete?id=" + id);
        return ResponseEntity.ok(emailService.deleteById(id));
    }
}
