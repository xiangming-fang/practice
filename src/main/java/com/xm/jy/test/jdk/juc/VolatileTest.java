package com.xm.jy.test.jdk.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: albert.fang
 * @Description: volatitle test
 * @Date: 2023/2/7 18:24
 */
public class VolatileTest {

    // volatile 用不了 ++ 场景
    private static volatile int tmp = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    tmp++;
                }
            });
        }
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(tmp);
    }


}
