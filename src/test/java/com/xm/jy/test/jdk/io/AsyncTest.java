package com.xm.jy.test.jdk.io;

import com.xm.jy.job_51.async.AsyncTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author: albert.fang
 * @date: 2020/11/27 10:32
 * @description: 异步调用
 */
@SpringBootTest
public class AsyncTest {

    @Resource
    private AsyncTask asyncTask;

    @Test
    public void print() throws InterruptedException {
        Instant begin = Instant.now();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
//        Thread.sleep(1000);
        TimeUnit.SECONDS.sleep(1);
        Instant end = Instant.now();
        System.out.printf("执行3个任务，共耗时：【%s】ms", Duration.between(begin,end).toMillis());
    }


}
