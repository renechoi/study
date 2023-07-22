package com.rene.core.domain.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rene.core.domain.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
