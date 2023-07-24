package com.rene.core.domain;


import java.time.LocalDateTime;

import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;
import com.rene.core.util.Period;

public class Event {
    private final Schedule schedule;

    public Event(Schedule schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return this.schedule.getId();
    }
    public LocalDateTime getStartAt() {
        return schedule.getStartAt();
    }

    public LocalDateTime getEndAt() {
        return schedule.getEndAt();
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }

    public String getTitle() {
        return schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(schedule.getStartAt(), schedule.getEndAt());
    }
}