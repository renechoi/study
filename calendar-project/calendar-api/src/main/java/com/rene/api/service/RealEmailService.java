package com.rene.api.service;

import static com.rene.api.dto.EngagementEmailStuff.*;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.rene.api.controller.api.BatchController;
import com.rene.api.dto.EngagementEmailStuff;
import com.rene.core.domain.entity.Share;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RealEmailService implements EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(stuff.getToEmail());
            helper.setSubject(stuff.getSubject());
            helper.setText(
                    templateEngine.process("engagement-email",
                            new Context(Locale.KOREAN, stuff.getProps())), true);
        };
        emailSender.send(preparator);
    }

    @Override
    public void sendAlarmMail(BatchController.SendMailBatchReq req) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(req.getUserEmail());
            helper.setSubject(req.getTitle());
            helper.setText(String.format(
                    "[%s] %s",
                    req.getStartAt().format(DateTimeFormatter.ofPattern(MAIL_TIME_FORMAT)),
                    req.getTitle()));
        };
        emailSender.send(preparator);
    }

    @Override
    public void sendShareRequestMail(String email, String email1, Share.Direction direction) {
        System.out.println("send share mail");
    }
}