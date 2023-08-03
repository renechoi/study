package com.example.springbatchpractice.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springbatchpractice.job.validator.LocalDateParameterValidator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@AllArgsConstructor
@Slf4j
public class AdvancedJobConfig {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job advancedJob(Step advancedStep, JobExecutionListener jobExecutionListener) {
		return jobBuilderFactory.get("advancedJob")
			.incrementer(new RunIdIncrementer())
			.validator(new LocalDateParameterValidator("targetDate"))
			.listener(jobExecutionListener)
			.start(advancedStep)
			.build();
	}

	@JobScope
	@Bean
	public JobExecutionListener jobExecutionListener() {
		return new JobExecutionListener() {
			@Override
			public void beforeJob(JobExecution jobExecution) {
				log.info("[JobExecutionListener] JobExecution is " + jobExecution.getStatus());
			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				if (jobExecution.getStatus() == BatchStatus.FAILED) {
					log.info("[JobExecutionListener] JobExecution is " + jobExecution.getStatus());
					// notificationservice -> notify
				}
			}
		};
	}


	@StepScope
	@Bean
	public StepExecutionListener stepExecutionListener() {
		return new StepExecutionListener() {
			@Override
			public void beforeStep(StepExecution stepExecution) {
				log.info("[StepExecutionListener#beforeStep] stepExecution is " + stepExecution.getStatus());
			}

			@Override
			public ExitStatus afterStep(StepExecution stepExecution) {
				log.info("[StepExecutionListener#afterStep] stepExecution is " + stepExecution.getStatus());
				return stepExecution.getExitStatus();
			}
		};
	}



	@JobScope
	@Bean
	public Step advancedStep(Tasklet advancedTasklet, StepExecutionListener stepExecutionListener) {
		return stepBuilderFactory.get("advancedStep")
			.listener(stepExecutionListener)
			.tasklet(advancedTasklet).build();
	}

	@StepScope
	@Bean
	public Tasklet advancedTasklet(@Value("#{jobParameters['targetDate']}") String targetDate) {
		return ((contribution, chunkContext) -> {
			log.info("[AdvancedJobConfig] JobParameter - targetDate = " + targetDate);
			log.info("[AdvancedJobConfig] executed advancedTasklet");
			return RepeatStatus.FINISHED;
		});
	}
}
