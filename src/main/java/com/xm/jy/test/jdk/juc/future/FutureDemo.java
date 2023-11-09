package com.xm.jy.test.jdk.juc.future;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class FutureDemo {

    static class SlowTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "速度慢的任务";
        }
    }

    static class FastTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "速度快的任务";
        }
    }

    // 1. 当 for 循环批量获取 Future 的结果时容易 block，get 方法调用时应使用 timeout 限制
    // 假设由于网络原因，第一个任务可能长达 1 分钟都没办法返回结果，那么这个时候，我们的主线程会一直卡着，影响了程序的运行效率。
    //
    // 此时我们就可以用 Future 的带超时参数的 get(long timeout, TimeUnit unit) 方法来解决这个问题。
    @Test
    public void test() {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //提交任务，并用 Future 接收返回结果
        ArrayList<Future> allFutures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Future<String> future;
            if (i == 0 || i == 1) {
                future = service.submit(new SlowTask());
            } else {
                future = service.submit(new FastTask());
            }
            allFutures.add(future);
        }

        for (int i = 0; i < allFutures.size(); i++) {
            Future<String> future = allFutures.get(i);
            try {
                String result = future.get(2,TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}