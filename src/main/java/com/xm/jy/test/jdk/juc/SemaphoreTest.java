package com.xm.jy.test.jdk.juc;

import java.util.concurrent.Semaphore;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/8 17:23
 * 信号量
 */
public class SemaphoreTest {

    // 哪个王八蛋说不同线程也能释放的，我这里不是失效了吗
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        Thread acquireT = new Thread(() -> {semaphore.tryAcquire();semaphore.release(20);});
        acquireT.start();
        try {
            acquireT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("还剩几个信号量：" + semaphore.availablePermits());
        Thread releaseT = new Thread(() -> semaphore.release(2));
        releaseT.start();
        try {
            acquireT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("还剩几个信号量：" + semaphore.availablePermits());
    }
}
