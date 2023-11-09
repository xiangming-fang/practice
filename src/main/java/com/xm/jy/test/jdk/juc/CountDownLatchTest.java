package com.xm.jy.test.jdk.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/8 17:56
 */
public class CountDownLatchTest {

    /// countdownlatch 是挺好理解的
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(() -> cdl.countDown()).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cdl.countDown();
        }).start();
//        cdl.await(5, TimeUnit.SECONDS);
        cdl.await();
        System.out.println("continue");
    }
}
