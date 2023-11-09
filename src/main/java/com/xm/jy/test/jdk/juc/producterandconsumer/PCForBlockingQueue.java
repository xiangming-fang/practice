package com.xm.jy.test.jdk.juc.producterandconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 11:10
 * 直接用阻塞队列实现pc（producter 和 consumer）,这个是最简单的pc实现
 */
public class PCForBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue(100);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true){
                    try {
                        String product = System.nanoTime()/10000 + "";
                        System.out.println("生产出：" + product);
                        queue.put(product);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    while (true){
                        System.out.println("消费内容：" + queue.take());
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
