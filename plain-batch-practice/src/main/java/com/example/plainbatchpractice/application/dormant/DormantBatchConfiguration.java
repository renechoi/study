package com.example.plainbatchpractice.application.dormant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.plainbatchpractice.batch.Job;
import com.example.plainbatchpractice.batch.Step;
import com.example.plainbatchpractice.batch.StepJobBuilder;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
            Step preDormantBatchStep,
            Step dormantBatchStep,
            DormantBatchJobExecutionListener listener
    ) {

        return new StepJobBuilder()
                .start(preDormantBatchStep)
                .next(dormantBatchStep)
                .build();
    }

    @Bean
    public Step preDormantBatchStep(
            AllCustomerItemReader itemReader,
            PreDormantBatchItemProcessor itemProcessor,
            PreDormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }

    @Bean
    public Step dormantBatchStep(
            AllCustomerItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }


    // 휴면전환 예정 1주일전인 사람에게 이메일을 발송한다!

}
