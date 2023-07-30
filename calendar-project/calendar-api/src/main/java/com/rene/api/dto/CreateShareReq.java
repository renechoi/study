package com.rene.api.dto;

import com.rene.core.domain.entity.Share;

import lombok.Data;

@Data
public class CreateShareReq {
    private final Long toUserId;
    private final Share.Direction direction;
}