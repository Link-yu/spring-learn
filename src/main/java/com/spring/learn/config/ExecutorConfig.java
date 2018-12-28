package com.spring.learn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@ConditionalOnProperty(prefix = "spring.async", name = "maxPoolSize")
@Component
public class ExecutorConfig {

    @Bean(value = "threadPoolExecutor")
    @Primary
    public ThreadPoolTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new VisiableThreadPoolTaskExecutor();
        //设置核心数5个
        threadPoolTaskExecutor.setCorePoolSize(5);
        //配置最大线程数5个
        threadPoolTaskExecutor.setMaxPoolSize(5);
        //配置队列数10000个
        threadPoolTaskExecutor.setQueueCapacity(10000);
        //配置线程名称
        threadPoolTaskExecutor.setThreadNamePrefix("test-thread-pool-");
        //如果达到最大线程数，队列中的任务如何处理
        //策略：callerRuns不在新线程中执行，而是有调用者所在的线程来执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
