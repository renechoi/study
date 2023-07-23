package com.rene.api.service;

import org.springframework.stereotype.Service;

import com.rene.core.domain.entity.Engagement;

@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(Engagement e) {
        System.out.println("메일발송 - attendee: " + e.getAttendee().getEmail() + ", scheduleId: " + e.getEvent().getId());
    }
}