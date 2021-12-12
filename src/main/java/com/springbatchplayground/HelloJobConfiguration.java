package com.springbatchplayground;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration  // 하나의 배치 Job을 정의
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;  // Job을 생성하는 빌더 팩토리
    private final StepBuilderFactory stepBuilderFactory;    // Step을 생성하는 빌더 팩토리

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")    // Job 이름 지정
                                .start(helloStep1())
                                .next(helloStep2())
                                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")     // Step 이름 지정
                                 .tasklet(new Tasklet() {
                                     @Override
                                     public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                         log.info("=============================");
                                         log.info("====== Step1 executed! ======");
                                         log.info("=============================");
                                         return RepeatStatus.FINISHED;   // 만약 상태 값을 지정하지 않으면 무한 반복
                                     }
                                 })
                                 .build();     // 비즈니스 로직 구현 단위
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                                 .tasklet(new Tasklet() {
                                     @Override
                                     public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                                         log.info("=============================");
                                         log.info("====== Step2 executed! ======");
                                         log.info("=============================");
                                         return RepeatStatus.FINISHED;
                                     }
                                 })
                                 .build();
    }
}
