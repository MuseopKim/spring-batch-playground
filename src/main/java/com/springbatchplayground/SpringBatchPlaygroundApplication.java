package com.springbatchplayground;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 배치 활성화
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchPlaygroundApplication.class, args);
    }
}
