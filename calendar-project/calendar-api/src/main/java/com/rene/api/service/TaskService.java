package com.rene.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rene.api.dto.AuthUser;
import com.rene.api.dto.TaskCreateReq;
import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.repository.ScheduleRepository;
import com.rene.core.service.UserService;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(TaskCreateReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(req.getTitle(), req.getDescription(), req.getTaskAt(), userService.getOrThrowById(authUser.getId()));
        scheduleRepository.save(taskSchedule);
    }

}