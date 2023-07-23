package com.rene.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// @EnableJpaAuditing
// @EntityScan("com.rene.core")
// @EnableJpaRepositories("com.rene.core")
// -> core의 configuration으로 이동 -> bean으로 등록하기 때문에 여기서 설정할 필요가 없어졌다.
@SpringBootApplication(scanBasePackages = {"com.rene.core", "com.rene.api"})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
