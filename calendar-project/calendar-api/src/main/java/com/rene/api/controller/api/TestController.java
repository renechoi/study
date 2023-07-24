package com.rene.api.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JavaMailSender emailSender;

    @GetMapping("/api/mail")
    public void sendMail() {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setTo("blabla@mail.mail");
            helper.setSubject("제목");
            helper.setText("테스트 메일");
        };
        emailSender.send(preparator);
    }

}