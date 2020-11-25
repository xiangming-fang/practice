package com.xm.jy.test.concurrent.threadpool.fixedthreadpool;

import com.xm.jy.test.concurrent.thread.thread5.threadLocal.ThreadAndLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/25 11:16
 * @description: 固定线程数的线程池 - fixthreadPool，创建固定线程池
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println("线程【" + Thread.currentThread().getName()+"】要睡觉了");
                    TimeUnit.MILLISECONDS.sleep(2);
                    System.out.println("线程【" + Thread.currentThread().getName()+"】睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
