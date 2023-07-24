package com.rene.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import com.rene.core.domain.RequestReplyType;

@Data
@NoArgsConstructor
public class ReplyEngagementReq {
    @NotNull
    private RequestReplyType type;
}