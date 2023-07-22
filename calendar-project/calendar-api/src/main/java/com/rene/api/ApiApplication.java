package com.rene.api;

import com.rene.core.SimpleEntity;
import com.rene.core.SimpleEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EntityScan("com.rene.core")
@EnableJpaRepositories("com.rene.core")
@RestController
@SpringBootApplication
public class ApiApplication {

    private final SimpleEntityRepository repository;

    public ApiApplication(SimpleEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SimpleEntity> findAll() {
        return repository.findAll();
    }

    @PostMapping("/save")
    public SimpleEntity saveOne() {
        return repository.save(new SimpleEntity());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
