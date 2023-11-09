package com.xm.jy.test.jdk.juc.visible;

import org.junit.jupiter.api.Test;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/8 16:18
 * 内存可见性问题复现：由于每个线程的工作内存和主内存数据不一致，没及时同步到主内存中
 * volatile 保证：线程对值的变动会立马同步到主内存中，保证其他线程的可见性
 */
public class MemoryVisibleProblemFix {

    volatile int a = 10;
    volatile int b = 20;

    private void change(){
        a = 30;
        b = a;
    }

    private void print(){
        System.out.println("b="+b+";a="+a);
    }

    @Test
    public void test(){
        while (true){
            MemoryVisibleProblemFix mv = new MemoryVisibleProblemFix();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mv.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mv.print();
            }).start();
        }
    }

}
