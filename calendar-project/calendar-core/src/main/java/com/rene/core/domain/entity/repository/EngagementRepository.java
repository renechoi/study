package com.rene.core.domain.entity.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rene.core.domain.entity.Engagement;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    List<Engagement> findAllByAttendeeIdInAndSchedule_EndAtAfter(List<Long> attendeeIds, LocalDateTime startAt);
}