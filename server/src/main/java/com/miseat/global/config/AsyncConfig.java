package com.miseat.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    public static final String THREAD_POOL_TASK_EXECUTOR = "threadPoolTaskExecutor";

    @Bean(name = THREAD_POOL_TASK_EXECUTOR)
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3); // 기본 스레드 수
        taskExecutor.setMaxPoolSize(30); // 최대 스레드 수
        taskExecutor.setQueueCapacity(100); // Queue 사이즈
        taskExecutor.setThreadNamePrefix("Executor-");
        return taskExecutor;
    }
}