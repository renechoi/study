package com.rene.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import com.rene.core.domain.ScheduleType;

@Builder
@Data
public class ForListNotificationDto implements ForListScheduleDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String title;
    private final LocalDateTime notifyAt;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}