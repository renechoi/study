package com.rene.api.service;

import com.rene.api.controller.api.BatchController;
import com.rene.api.dto.EngagementEmailStuff;
import com.rene.core.domain.entity.Share;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
    void sendShareRequestMail(String email, String email1, Share.Direction direction);
}