package com.rene.core.domain;

import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;

public class Task {

    private Schedule schedule;

    public Task (Schedule schedule) {
        this.schedule = schedule;
    }

    public User getWriter() {
        return this.schedule.getWriter();
    }
}
