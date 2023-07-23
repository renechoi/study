package com.rene.api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskCreateReq {
    private final String title;
    private final String description;
    private final LocalDateTime taskAt;
}