package com.rene.api.controller.api;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import com.rene.api.service.EmailService;

@RequiredArgsConstructor
@RestController
public class BatchController {
    private final EmailService emailService;

    @PostMapping("/api/batch/send/mail")
    public ResponseEntity<Void> sendMail(@RequestBody List<SendMailBatchReq> req) {
        req.forEach(emailService::sendAlarmMail);
        return ResponseEntity.ok().build();
    }

    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static public class SendMailBatchReq {
        private Long id;
        private LocalDateTime startAt;
        private String title;
        private String userEmail;
    }
}