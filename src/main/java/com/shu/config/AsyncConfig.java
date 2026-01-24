package com.shu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("mailExecutor")
    public Executor mailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);        // 核心线程
        executor.setMaxPoolSize(5);         // 最大线程
        executor.setQueueCapacity(100);     // 队列长度
        executor.setThreadNamePrefix("mail-async-");
        executor.initialize();
        return  executor;
    }
}
