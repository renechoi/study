package com.rene.api.service;

import com.rene.api.dto.EngagementEmailStuff;
import com.rene.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff e);
}