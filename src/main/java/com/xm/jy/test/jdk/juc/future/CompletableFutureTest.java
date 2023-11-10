package com.xm.jy.test.jdk.juc.future;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/9 16:48
 *
 * CompletableFuture 使用
 * https://kaiwu.lagou.com/course/courseInfo.htm?courseId=16#/detail/pc?id=289
 */
public class CompletableFutureTest {

    public static void main(String[] args)
            throws Exception {
        CompletableFutureTest completableFutureDemo = new CompletableFutureTest();
        System.out.println(completableFutureDemo.getPrices());
    }

    private Set<Integer> getPrices() {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(123, prices));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(456, prices));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(789, prices));

        // 把多个 task 汇总
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        try {
            allTasks.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ignored) {
        }
        return prices;
    }

    private static class Task implements Runnable {

        Integer productId;
        Set<Integer> prices;

        public Task(Integer productId, Set<Integer> prices) {
            this.productId = productId;
            this.prices = prices;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                Thread.sleep((long) (Math.random() * 4000));
                price = (int) (Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }

    // completableFuture中 run 开头方法 =》 异步执行，无返回值
    @Test
    public void completableFutureRunTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            int i = 10, j = 9;
            System.out.println(Thread.currentThread().getName() + (i % j));
        }, executorService);
        executorService.shutdown();

        // 这个情况是内置的线程池执行的，如果在jvm启动的时候指定
        // 并行度parallelism由jvm启动参数java.util.concurrent.ForkJoinPool.common.parallelism来指定，
        // 然后对其进行校验，若parallelism<=0，且机器逻辑核心数-1后小于等于0，则赋值为1
        // parallelism 大于1 用forkjoinpool，否则用threadperTaskExecutor,其底层就是新建一个线程执行传入的任务
        CompletableFuture<Void> runAsyncWithoutSpecificThreadPool = CompletableFuture.runAsync(() -> {
            int i = 10, j = 91;
            System.out.println(Thread.currentThread().getName() + (i % j));
        });
    }


    // completableFuture中 supply开头方法 =》 异步执行，有返回值
    @Test
    public void completableFutureSupplyTest() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            int i = 10, j = 9;
//            System.out.println(Thread.currentThread().getName() + (i % j));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i%j;
        }, executorService);
        executorService.shutdown();

        CompletableFuture<Integer> supplyAsyncWithoutSpecificThreadPoolFuture = CompletableFuture.supplyAsync(() -> {
            int i = 10, j = 91;
//            System.out.println(Thread.currentThread().getName() + (i % j));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i%j;
        });

        System.out.println(supplyAsyncFuture.get());
        System.out.println(supplyAsyncWithoutSpecificThreadPoolFuture.get());
    }

    // CompletableFuture.whenComplete()：用于接收带有返回值的CompletableFuture对象，无法修改返回值。
    //
    //CompletableFuture.exceptionally()：用于处理异常，只要异步线程中有抛出异常，则进入该方法，修改返回值。
    //
    //CompletableFuture.handle()：用于处理返回结果，可以接收返回值和异常，可以对返回值进行修改。
    @Test
    public void whenCompleteOrexceptionOrhandleTest() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 异步起线程执行业务 有返回值
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((res,exc)->{
            // 可以接收到返回值和异常类型，但是无法处理异常
            System.out.println("异步任务成功完成了...结果是：" + res + ";异常是：" + exc);
        }).exceptionally(throwable -> {
            // 处理异常，返回一个自定义的值，和上边返回值无关。
            return 10;
        });
        // 应该返回异常中的10
        System.out.println(future.get() == 10);

        // 方法执行完成后的处理
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((res,thr)->{
            // 无论线程是否正确执行，都会执行这里，可以对返回值进行操作。
            System.out.println(res);
            System.out.println(thr);
            if(res != null){
                return res * 2;
            }
            if(thr != null){
                return 20;
            }
            return 0;
        });
        // 异常不为空，所以这里应该是返回20
        System.out.println(future1.get() == 20);

        executor.shutdown();

    }


    /**
     * // 使线程串行执行，无入参，无返回值
     * public CompletableFuture<Void> thenRun(Runnable action);
     * public CompletableFuture<Void> thenRunAsync(Runnable action);
     * public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor);
     *
     * // 使线程串行执行，有入参，无返回值
     * public CompletableFuture<Void> thenAccept(Consumer<? super T> action);
     * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);
     *
     * // 使线程串行执行，有入参，有返回值
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn);
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn);
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor);
     */
    // 不以Async结尾的方法，都是在执行串行的时候，使用执行上一个方法的线程，也就是说从头串行到最后一个任务，使用的是同一个线程。
    // 而以Async结尾的方法，每串行一个方法，都会使用一个新线程。
    @Test
    public void syncAndAsyncTest() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 两个任务串行执行，任务2不用任务1的返回值，并且任务2无返回值。
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1：当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1：运行结果：" + i);
            return i;
        }, executor).thenRunAsync(() -> {
            System.out.println("任务2启动了：当前线程：" + Thread.currentThread().getId());
        }, executor);

        // 两个任务串行执行，任务2要使用任务1的返回值，并且任务2无返回值。
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1：当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1：运行结果：" + i);
            return i;
        }, executor).thenAcceptAsync(res -> {
            System.out.println("任务2启动了：当前线程：" + Thread.currentThread().getId() + " result:" + res);
        }, executor);

        // 两个任务串行执行，任务2要使用任务1的返回值，并且返回任务2的返回值。
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1：当前线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1：运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了：当前线程：" + Thread.currentThread().getId() + " result:" + res);
            return "Hello" + res;
        }, executor);
        // 有返回值时，需要使用CompletableFuture.get()方法，等待异步线程执行结束，从而获取到异步线程的返回值。
        String s = future3.get();
        System.out.println(s);

        executor.shutdown();

    }

    /**
     * 两任务并行执行,都执行完成，再执行新任务
     * // 线程并行执行完成，并且执行新任务action，新任务无入参，无返回值
     * public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
     * public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
     * public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor);
     *
     * // 线程并行执行完成，并且执行新任务action，新任务有入参，无返回值
     * public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
     * public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
     * public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor);
     *
     * // 线程并行执行完成，并且执行新任务action，新任务有入参，有返回值
     * public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor);
     */
    @Test
    public void asyncTaskFinish() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 任务1
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束：");

            return i;
        }, executor);

        // 任务2
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");

            return "Hello";
        }, executor);

        // 任务1和任务2都完成，在不使用任务1和任务2的返回值情况下执行任务3，并且任务3没有返回值
        CompletableFuture<Void> future1 = future01.runAfterBothAsync(future02,
                () -> System.out.println("任务3开始"), executor);

        // 任务1和任务2都完成，使用任务1和任务2的返回值情况下执行任务3，并且任务3没有返回值
        CompletableFuture<Void> future2 = future01.thenAcceptBothAsync(future02,
                (f1, f2) -> System.out.println("任务3开始，之前的结果" + f1 + "-->" + f2),
                executor);

        // 任务1和任务2都完成，使用任务1和任务2的返回值情况下执行任务3，并且任务3有返回值
        CompletableFuture<String> future3 = future01.thenCombineAsync(future02,
                (f1, f2) -> f1 + ":" + f2 + "->haha",
                executor);
        String str = future3.get();
        System.out.println(str);

        executor.shutdown();
    }

    /**
     * 两任务并行执行，其中一个执行完，就执行新任务。
     * // 任务并行执行，只要其中有一个执行完，就开始执行新任务action，新任务无入参，无返回值
     * public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action);
     * public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
     * public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor);
     *
     * // 任务并行执行，只要其中有一个执行完，就开始执行新任务action，新任务有入参（入参类型为Object，因为不确定是哪个任务先执行完成），无返回值
     * public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
     * public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action);
     * public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action,Executor executor);
     *
     * // 任务并行执行，只要其中有一个执行完，就开始执行新任务action，新任务有入参（入参类型为Object，因为不确定是哪个任务先执行完成），有返回值
     * public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
     * public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn);
     * public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn,Executor executor);
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void asyncTaskEitherFinish() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 任务1
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 100 / 2;
            try {
                // 通过睡眠方式控制哪个task先结束
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结束：");

            return i;
        }, executor);

        // 任务2
        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结束");

            return 56;
        }, executor);

        // 任务1和任务2并行执行，只要有一个执行完成，就执行任务3，不使用任务1 或 任务2线程的结果，并且任务3没有返回值
        future01.runAfterEitherAsync(future02,()-> System.out.println("任务3开始，之前的结果"), executor);

        // 任务1和任务2并行执行，只要有一个执行完成，就执行任务3，使用任务1 或 任务2线程的结果，并且任务3没有返回值
        future01.acceptEitherAsync(future02, (res)-> System.out.println("任务3开始，之前的结果" + res), executor);

        // 任务1和任务2并行执行，只要有一个执行完成，就执行任务3，使用任务1 或 任务2线程的结果，并且任务3有返回值
        CompletableFuture<Object> future = future01.applyToEitherAsync(future02, (res) -> {
            System.out.println("任务3开始，之前的结果" + res);
            return res.toString() + "->哈哈";
        }, executor);

        // get带有阻塞功能，让异步线程结束，得到结果
        System.out.println(future.get());

        executor.shutdown();
    }

    /**
     * 多任务组合（只要有一个执行完就返回）
     * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
     */
    @Test
    public void anyOfTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        CompletableFuture<String> future01 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务1";
        }, executorService);
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务2";
        }, executorService);
        CompletableFuture<String> future03 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务3";
        }, executorService);

        // 只要异步线程队列有一个任务率先完成就返回，这个特性可以用来获取最快的那个线程结果。
        // 哪个任务执行的快，就返回哪个
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future01, future02, future03);

        // 获取若干个任务中最快完成的任务结果
        // .join()和.get()都会阻塞并获取线程的执行情况
        // .join()会抛出未经检查的异常，不会强制开发者处理异常 .get()会抛出检查异常，需要开发者处理
        Object o1 = anyOf.get();
        Object o2 = anyOf.join();

        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o1 == o2);
        executorService.shutdown();

    }

    /**
     * 多任务组合（全部执行完才返回）
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs);
     */
    @Test
    public void allOfTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        CompletableFuture<String> future01 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务1";
        }, executorService);
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务2";
        }, executorService);
        CompletableFuture<String> future03 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务3";
        }, executorService);

        // 串联起若干个线程任务, 没有返回值
        CompletableFuture<Void> all = CompletableFuture.allOf(future01, future02, future03);
        // 等待所有线程执行完成
        // .join()和.get()都会阻塞并获取线程的执行情况
        // .join()会抛出未经检查的异常，不会强制开发者处理异常 .get()会抛出检查异常，需要开发者处理
        System.out.println(all.join());
        System.out.println(all.get());
        executorService.shutdown();
    }


}
