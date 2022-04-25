package com.bithumbsystems.bootstarterproject.common.response;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableMongoAuditing
@EnableReactiveMongoRepositories
//@EnableMongoRepositories
public class BootStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootStarterApplication.class, args);
    }
}
