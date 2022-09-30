package com.isoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EntityScan("com.isoft.models")
@EnableJpaRepositories(value = "com.isoft.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditingImp")
public class DemoApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
    }
}
