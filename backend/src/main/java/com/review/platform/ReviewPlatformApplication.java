package com.review.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReviewPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewPlatformApplication.class, args);
    }
} 