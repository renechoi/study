package com.rene.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rene.core.util.BCryptEncryptor;
import com.rene.core.util.Encryptor;

@EntityScan("com.rene.core")
@EnableJpaRepositories("com.rene.core")
@EnableJpaAuditing
@Configuration
public class CoreConfig {

    @Bean
    public Encryptor bcryptEncryptor() {
        return new BCryptEncryptor();
    }

}