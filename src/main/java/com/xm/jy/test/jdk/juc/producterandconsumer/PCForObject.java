package com.xm.jy.test.jdk.juc.producterandconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 12:26:13
 * 用 Object中的wait方法和notify方法  + synchronized 关键字
 * 其主要思想就是用上述方法维护一个自己定义的队列，使得这个队列有阻塞的功能
 */
public class PCForObject {

    private Queue<String> queue;

    private final int max = 75;

    private final Object lock = new Object();

    public PCForObject(Queue queue){
        this.queue = queue;
    }

    public void put(String value) throws InterruptedException {
        synchronized (lock){
            while (queue.size() == max){
                lock.wait();
            }
            queue.add(value);
            // todo：感觉这里必须是唤醒全部的
            // 原因：如果随机唤醒的话，可能只是唤醒了和当前线程同样类型的（生产者），但是此时消费者是全部阻塞的。
            lock.notifyAll();
        }
    }

    public String take() throws InterruptedException {
        synchronized (lock){
            while (queue.isEmpty()){
                lock.wait();
            }
            String value = queue.poll();
            // todo：感觉这里必须是唤醒全部的
            // 原因：如果随机唤醒的话，可能只是唤醒了和当前线程同样类型的（消费者），但是此时生产者是全部阻塞的。
            lock.notifyAll();
            return value;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        PCForObject customizedBlockingQueue = new PCForObject(queue);
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true){
                    String product = System.nanoTime() / 10000 + "";
                    try {
                        customizedBlockingQueue.put(product);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("生产出：" + product);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true){
                    String product = null;
                    try {
                        product = customizedBlockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
