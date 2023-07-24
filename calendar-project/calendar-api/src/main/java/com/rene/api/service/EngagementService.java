package com.rene.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rene.api.dto.AuthUser;
import com.rene.core.domain.RequestReplyType;
import com.rene.core.domain.RequestStatus;
import com.rene.core.domain.entity.Engagement;
import com.rene.core.domain.entity.repository.EngagementRepository;
import com.rene.core.exception.CalendarException;
import com.rene.core.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        return engagementRepository.findById(engagementId)
                .filter(Engagement::isRequested)
                .filter(engagement -> engagement.getAttendee().getId().equals(authUser.getId()))
                .map(engagement -> engagement.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getStatus();
    }
}