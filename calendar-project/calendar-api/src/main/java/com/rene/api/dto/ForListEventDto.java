package com.rene.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.rene.core.domain.ScheduleType;


@Builder
@Data
public class ForListEventDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.EVENT;
    }
}