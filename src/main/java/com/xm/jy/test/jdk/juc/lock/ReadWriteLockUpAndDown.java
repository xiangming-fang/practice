package com.xm.jy.test.jdk.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 18:16
 * 读写锁的升降级 使用
 */
public class ReadWriteLockUpAndDown {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    // 在更新缓存的时候，如何利用锁的降级功能。
    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // 在获取写锁之前，必须首先释放读锁。
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // 这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他线程修改了数据。
                if (!cacheValid) {
                    // todo 修改数据的代码
                    data = new Object();
                    cacheValid = true;
                }
                // 在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                // todo 这么变态，你在无中生 “读锁”
                rwl.readLock().lock();
            } finally {
                // 释放了写锁，但是依然持有读锁
                rwl.writeLock().unlock();
            }
        }

        try {
            System.out.println(data);
        } finally {
            // 释放读锁
            rwl.readLock().unlock();
        }
    }



}
