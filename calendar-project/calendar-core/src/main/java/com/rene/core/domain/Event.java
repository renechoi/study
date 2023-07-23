package com.rene.core.domain;


import java.time.LocalDateTime;

import com.rene.core.domain.entity.Schedule;
import com.rene.core.domain.entity.User;

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

    /**
     * 주어진 startAt과 endAt 사이의 시간이 현재 Event 객체의 Schedule 시간과 겹치는지 여부를 판단한다.
     * 일정이 겹치기 위한 조건은 다음과 같으므로 And 조건으로 연결된다.
     * -> 현재 Event의 시작 시간은 주어진 일정의 종료 시간보다 이전이어야 한다.
     * -> 주어진 일정의 시작 시간은 현재 Event의 종료 시간보다 이전이어야 한다.
     *
     * 1) this.getStartAt().isBefore(endAt)는 현재 Event의 시작 시간이 주어진 endAt보다 이전인지를 판단한다.
     * 즉, 현재 일정이 주어진 일정의 끝나는 시간 전에 시작하는지를 확인한다.
     *
     * 2) startAt.isBefore(this.getEndAt())는 주어진 startAt이 현재 Event의 끝나는 시간보다 이전인지를 판단한다.
     * 즉, 주어진 일정이 현재 일정이 끝나기 전에 시작하는지를 확인한다.
     *
     * 만약 두 조건 모두 만족한다면,
     * 즉 현재 Event의 시작 시간이 주어진 endAt보다 이전이고,
     * 주어진 startAt이 현재 Event의 끝나는 시간보다 이전이라면, 두 일정은 겹치는 것으로 판단된다.
     *
     * @param startAt
     * @param endAt
     * @return
     */
    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.getStartAt().isBefore(endAt) && startAt.isBefore(this.getEndAt());
    }
}