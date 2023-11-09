package com.xm.jy.test.jdk.juc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 15:17
 * jdk 提供的线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.execute(() -> System.out.println("execute没有返回值"));
        Future<?> futures = singleThreadScheduledExecutor.submit(() -> System.out.println("submit有返回值"));
        singleThreadScheduledExecutor.shutdown();
        List<Runnable> runnables = singleThreadScheduledExecutor.shutdownNow();
    }
}
