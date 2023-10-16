package com.example.plainbatchpractice.application.dormant;

import org.springframework.stereotype.Component;

import com.example.plainbatchpractice.EmailProvider;
import com.example.plainbatchpractice.batch.JobExecution;
import com.example.plainbatchpractice.batch.JobExecutionListener;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // no-op
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // 비즈니스 로직
        emailProvider.send(
                "admin@fastcampus.com",
                "배치 완료 알림",
                "DormantBatchJob이 수행되었습니다. status :" + jobExecution.getStatus()
        );
    }

}
