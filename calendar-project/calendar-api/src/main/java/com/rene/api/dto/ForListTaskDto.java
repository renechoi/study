package com.rene.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.rene.core.domain.ScheduleType;

@Builder
@Data
public class ForListTaskDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.TASK;
    }
}