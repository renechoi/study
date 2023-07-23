package com.rene.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.rene.api.dto.AuthUser;
import com.rene.api.dto.CreateEventReq;
import com.rene.core.domain.RequestStatus;
import com.rene.core.domain.entity.Engagement;
import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.repository.EngagementRepository;
import com.rene.core.domain.entity.repository.ScheduleRepository;
import com.rene.core.service.UserService;

@Service
@RequiredArgsConstructor
public class EventService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;
    private final EmailService emailService;

    @Transactional
    public void create(CreateEventReq req, AuthUser authUser) {
        // attendees 의 스케쥴 시간과 겹치지 않는지?
        final List<Engagement> engagementList =
                engagementRepository.findAllByAttendeeIdInAndSchedule_EndAtAfter(req.getAttendeeIds(),
                        req.getStartAt());
        if (engagementList
                .stream()
                .anyMatch(e -> e.getEvent().isOverlapped(req.getStartAt(), req.getEndAt())
                        && e.getStatus() == RequestStatus.ACCEPTED)) {
            throw new RuntimeException("cannot make engagement. period overlapped.");
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(eventSchedule);
        req.getAttendeeIds().stream()
                .map(userService::getOrThrowById)
                .forEach(user -> {
                    final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
                    emailService.sendEngagement(e);
                });
    }

}