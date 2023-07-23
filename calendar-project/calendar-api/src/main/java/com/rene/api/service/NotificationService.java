package com.rene.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rene.api.dto.AuthUser;
import com.rene.api.dto.CreateNotificationReq;
import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;
import com.rene.core.domain.entity.repository.ScheduleRepository;
import com.rene.core.service.UserService;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(CreateNotificationReq req, AuthUser authUser) {
        final User writer = userService.getOrThrowById(authUser.getId());
        req.getRepeatTimes()
                .forEach(notifyAt ->
                        scheduleRepository.save(Schedule.notification(req.getTitle(), notifyAt, writer)));
    }
}