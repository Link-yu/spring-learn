package com.spring.learn.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private void showThreadPool(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        if (threadPoolExecutor == null) {
            return;
        }
//        System.out.println("name:" + prefix);
//        System.out.println("NamePrefix:" + this.getThreadNamePrefix());
//        System.out.println("TaskCount:" + threadPoolExecutor.getTaskCount());
//        System.out.println("CompletedTaskCount:" + threadPoolExecutor.getCompletedTaskCount());
//        System.out.println("ActiveCount:" + threadPoolExecutor.getActiveCount());
//        System.out.println("Queue:" + threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showThreadPool("1. do execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPool("2. do execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPool("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPool("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPool("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPool("2. do submitListenable");
        return super.submitListenable(task);
    }
}
