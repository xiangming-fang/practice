package com.xm.jy.test.jdk.juc.producterandconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 11:41
 * 用 ReentrantLock 中的Condition实现pc
 * 其主要思想就是用condition维护一个自己定义的队列，使得这个队列有阻塞的功能
 */
public class PCForCondition {

    private Queue<String> queue;

    private final int max = 75;

    private final ReentrantLock lock = new ReentrantLock();

    // 用于存放端判断
    private final Condition p = lock.newCondition();

    // 用于取值端判断
    private final Condition c = lock.newCondition();

    public PCForCondition(Queue queue){
        this.queue = queue;
    }

    public void put(String value) {
        lock.lock();
        try {
            while (queue.size() == max) {
                try {
                    p.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(value);
            c.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = queue.poll();
            p.signal();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        PCForCondition customizedBlockingQueue = new PCForCondition(queue);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true){
                    String product = System.nanoTime() / 10000 + "";
                    customizedBlockingQueue.put(product);
                    System.out.println("生产出：" + product);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true){
                    String product = customizedBlockingQueue.take();
                    System.out.println("消费了：" + product);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
