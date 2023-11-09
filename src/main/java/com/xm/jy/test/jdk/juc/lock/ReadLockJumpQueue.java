package com.xm.jy.test.jdk.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author xiangming.fang
 * @date 2023年11月9日 18:08:52
 * 演示读锁不插队
 */
public class ReadLockJumpQueue {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock
            .readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock
            .writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    // 前两个线程可以同时获取读锁进行读取
    // 第三个线程进来持有写锁，因为此时有读锁，所以要排队
    // 第四个线程进来，写锁排在头部，所以不能插队，放最后执行
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> read(),"Thread-2").start();
        new Thread(() -> read(),"Thread-4").start();
        new Thread(() -> write(),"Thread-3").start();
        new Thread(() -> read(),"Thread-5").start();
        // 延迟，尽量让子线程走完
        Thread.sleep(10000);
    }
}