package com.xm.jy.test.jdk.juc.future;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 15:52
 * future =》获取 单次 提交给线程池的任务结果
 * Future 相当于一个存储器，它存储了 Callable 的 call 方法的任务结果
 */
public class FutureTest {

    static class CallableTask1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }

    static class CallableTask2 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            throw new IllegalArgumentException("抛出个异常");
        }
    }

    static class CallableTask3 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }

    // get 获取返回结果
    @Test
    public void getTest() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask1());
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

    // isDone 判断任务是否执行完成，抛出异常也算完成 true - 已完成
    @Test
    public void isDoneTest() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask2());
        // 为线程池中的线程完成任务概率加大，起到延迟作用
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
        }
        System.out.println(future.isDone());
        // 虽然在任务执行一开始时就抛出了异常，但是真正要等到我们执行 get 的时候，才看到了异常。
        System.out.println(future.get());
        service.shutdown();
    }

    // cancel 取消放进线程池中的任务 true -> 取消完成
    // isCancelled() 方法：判断是否被取消
    @Test
    public void cancelTest() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask1());
        // 这个任务正在执行，这个时候执行 cancel 方法是不会直接取消这个任务的，而是会根据我们传入的参数做判断。cancel 方法是必须传入一个参数，该参数叫作  mayInterruptIfRunning，它是什么含义呢？
        // 如果传入的参数是 true，执行任务的线程就会收到一个中断的信号，正在执行的任务可能会有一些处理中断的逻辑，进而停止，这个比较好理解。
        // 如果传入的是 false 则就代表不中断正在运行的任务，也就是说，本次 cancel 取消了未运行的任务
        System.out.println(future.cancel(false));
        System.out.println(future.isCancelled());
        service.shutdown();
    }

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

    // 除了往线程池中提交用submit会有future返回值，同样可以用futuretask来获取future类
    // 演示futureTask用法
    @Test
    public void futureTaskTest() {

        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
        new Thread(integerFutureTask).start();

        try {
            System.out.println("task运行结果：" + integerFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
