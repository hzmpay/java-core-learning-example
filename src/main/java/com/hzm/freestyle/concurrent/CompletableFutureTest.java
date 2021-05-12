package com.hzm.freestyle.concurrent;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 注意点：1.CompletableFuture构造方法不传
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月28日
 */
@Slf4j
public class CompletableFutureTest {

    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            log.info("开始监听执行结果 ==========》");
            future.complete("OK");
        }).start();
        log.info(future.get());
    }

    /**
     * 注意：supplyAsync不传入线程池，默认使用的是ForkJoinPool的common，创建的是守护线程
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void supplyAsyncTest() throws ExecutionException, InterruptedException {
        // 默认使用ForkJoinPool
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                log.info("ForkJoinPool 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
                TimeUnit.SECONDS.sleep(2);
                log.info("ForkJoinPool   执行完毕");
                return "ForkJoinPool";
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                log.info("executorService 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
                TimeUnit.SECONDS.sleep(2);
                log.info("executorService 执行完毕");
                return "executorService";
            }
        }, executorService);
        // 注释掉的话，执行完毕的ForkJoinPool输出结果不会输出（前提：主线程结束之前future1未执行）
        // 因为是守护线程，所以和主线程一起结束
//        log.info(future1.get());
//        log.info(future2.get());
        executorService.shutdown();
    }

    /**
     * 注意：runAsync不传入线程池，默认使用的是ForkJoinPool的common，创建的是守护线程
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void runAsyncTest() throws ExecutionException, InterruptedException {
        // 默认使用ForkJoinPool
        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            log.info("ForkJoinPool 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("ForkJoinPool   执行完毕");
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture future2 = CompletableFuture.runAsync(() -> {
            log.info("executorService 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("executorService 执行完毕");
        }, executorService);
        // 注释掉的话，执行完毕的ForkJoinPool输出结果不会输出（前提：主线程结束之前future1未执行）
        // 因为是守护线程，所以和主线程一起结束
//        log.info(future1.get());
//        log.info(future2.get());
        executorService.shutdown();
    }

    public static void allOfAndAnyOfTest() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("future2 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future2 执行完毕");
            return "future2";
        });
//        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2);
        // 当所有任务都完成时才返回（注意，这是异步的，如果用的是守护线程，主程序结束前没执行完就会跟着结束）
//        System.out.println(allFuture.get());

        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);
        // 其中一个任务执行完成立马返回
        System.out.println(anyFuture.get());
    }

    public static void getNowTest() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });

        TimeUnit.SECONDS.sleep(3);
        // 调用getNow的时候如果future还未执行完成则返回传入的默认值，执行完成则返回正常返回值
        System.out.println(future1.getNow("OK"));
    }

    public static void joinTest() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });
        // join和Thread的join效果一样，阻塞主线程
        future1.join();
        System.out.println("主线程开始执行");
    }

    public static void whenTest() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            int a = 0/1;
            log.info("future1 执行完毕");
            return "future1";
        });
        future1.whenComplete((t, u) -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("输出结果：t = {}, u = {}", t, u);
        });
        System.out.println("主线程开始执行");
        Thread.sleep(5000L);
    }



    public static void main(String[] args) throws Exception {
        whenTest();
//        joinTest();
//        getNowTest();
//        allOfAndAnyOfTest();
//        runAsyncTest();
//        supplyAsyncTest();
    }

}
