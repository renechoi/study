package com.rene.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

import com.rene.api.dto.AuthUser;
import com.rene.api.dto.CreateEventReq;
import com.rene.api.dto.EngagementEmailStuff;
import com.rene.core.domain.RequestStatus;
import com.rene.core.domain.entity.Engagement;
import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;
import com.rene.core.domain.entity.repository.EngagementRepository;
import com.rene.core.domain.entity.repository.ScheduleRepository;
import com.rene.core.exception.CalendarException;
import com.rene.core.exception.ErrorCode;
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
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule eventSchedule = Schedule.event(req.getTitle(), req.getDescription(), req.getStartAt(), req.getEndAt(), userService.getOrThrowById(authUser.getId()));

        scheduleRepository.save(eventSchedule);

        final List<User> attendeeList = req.getAttendeeIds().stream().map(userService::getOrThrowById).collect(toList());

        attendeeList.forEach(user -> {
            final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
            emailService.sendEngagement(
                    new EngagementEmailStuff(
                            e.getId(),
                            e.getAttendee().getEmail(),
                            attendeeList.stream().map(User::getEmail).collect(toList()),
                            e.getEvent().getTitle(),
                            e.getEvent().getPeriod()
                    ));
        });
    }

}