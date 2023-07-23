package com.rene.api.service;

import com.rene.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement e);
}