package com.web.backend.listeners.listener;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import java.util.concurrent.*;

@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("threads")
public class DefaultExecutorServiceFactory implements ExecutorServiceFactory {

    private int threadPoolSize;

    private ExecutorService service;

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public ExecutorService getExecutorService() {
        if (this.service == null) {
            this.service = Executors.newFixedThreadPool(threadPoolSize);
        }

        return this.service;
    }
}
