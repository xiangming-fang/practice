package com.xm.jy.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: albert.fang
 * @Description: volatitle test
 * @Date: 2023/2/7 18:24
 */
public class VolatileTest {

    // volatile 用不了 ++ 场景
    private static volatile int tmp = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    tmp++;
                }
            });
        }
        executorService.shutdown();
        System.out.println(tmp);
    }


}
