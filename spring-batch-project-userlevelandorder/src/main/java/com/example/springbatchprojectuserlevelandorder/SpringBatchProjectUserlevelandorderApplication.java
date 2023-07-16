package com.example.springbatchprojectuserlevelandorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class SpringBatchProjectUserlevelandorderApplication {

	/**
	 * Asinc 실행시 종료가 안되는 상황을 해결하기 위한 방법으로 명시적 exit 설정
	 */
	public static void main(String[] args) {
		System.exit(
			SpringApplication.exit(
		SpringApplication.run(SpringBatchProjectUserlevelandorderApplication.class, args)));
	}

	/**
	 * Spring에서 기본 TaskExecutor가 있지만 현재 사용하고자 하는 ThreadPoolTaskExecutor가 사용되도록 primary 애노테이션 사용
	 * 해당 executor는 pool 안에서 스레드를 생성해놓고 사용할 수 있게 하므로 좀 더 효율적으로 사용
	 * @return
	 */
	@Bean
	@Primary
	TaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(20);
		taskExecutor.setThreadNamePrefix("batch-thread");
		taskExecutor.initialize();
		return taskExecutor;
	}

}
