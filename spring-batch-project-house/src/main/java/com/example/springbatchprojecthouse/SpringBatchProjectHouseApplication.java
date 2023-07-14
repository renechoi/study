package com.example.springbatchprojecthouse;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchProjectHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchProjectHouseApplication.class, args);
	}

}
