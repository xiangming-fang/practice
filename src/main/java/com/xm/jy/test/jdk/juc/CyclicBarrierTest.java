package com.xm.jy.test.jdk.juc;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/8 17:56
 */
public class CyclicBarrierTest {


    class Task implements Runnable{

        int id;
        CyclicBarrier cb;

        public Task(int id,CyclicBarrier cb){
            this.id = id;
            this.cb = cb;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("同学 " + id + " 到了" );
            cb.await();
            Thread.sleep(1000);
        }
    }

    @Test
    public void test() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("凑齐两人，一起去玩");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(new Task(i + 1,cyclicBarrier)).start();
        }
    }


}
