package com.xm.jy.job_51.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: albert.fang
 * @date: 2020/11/27 10:38
 * @description: 异步任务
 */
@Component
public class AsyncTask {

    private static Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Async
    public void task1(){
        try {
            Thread.sleep(100);
            System.out.println("任务1，睡了100ms，再开始走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void task2(){
        try {
            Thread.sleep(100);
            System.out.println("任务2，睡了100ms，再开始走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void task3(){
        try {
            Thread.sleep(100);
            System.out.println("任务3，睡了100ms，再开始走");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
