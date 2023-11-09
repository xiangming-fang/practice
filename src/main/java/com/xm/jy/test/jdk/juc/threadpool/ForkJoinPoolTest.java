package com.xm.jy.test.jdk.juc.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 15:26
 * 这个 ForkJoinPoolTest 是要提交到ForkJoinPool里的具体任务
 */
public class ForkJoinPoolTest extends RecursiveTask<Integer> {

    int n;

    public ForkJoinPoolTest(int n) {
        this.n = n;
    }

    @Override
    public Integer compute() {
        if (n <= 1) {
            return n;
        }
        ForkJoinPoolTest f1 = new ForkJoinPoolTest(n - 1);
        f1.fork();
        ForkJoinPoolTest f2 = new ForkJoinPoolTest(n - 2);
        f2.fork();
        return f1.join() + f2.join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new ForkJoinPoolTest(i));
            System.out.println(task.get());
        }
    }
}
