package com.rene.api.service;

import com.rene.api.controller.api.BatchController;
import com.rene.api.dto.EngagementEmailStuff;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
}