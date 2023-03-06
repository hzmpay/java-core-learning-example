package com.hzm.freestyle.concurrent;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    public static void whenCompleteTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            // 演示异常效果
            int a = 1 / 0;
            log.info("future1 执行完毕");
            return "future1";
        });
        // future1先执行再执行whenComplete传递的action，如果future1有异常，则直接执行action
        future1.whenComplete((t, u) -> {
            log.info("whenComplete 开始执行");
            log.info("当前线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        });
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
        Thread.sleep(5000L);
    }

    public static void whenCompleteTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });
        // future1先执行再执行whenComplete传递的action，如果future1有异常，则直接执行action
        future1.whenComplete((t, u) -> {
            log.info("whenComplete 开始执行");
            log.info("whenComplete线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        });
        // future1和whenComplete用的是同一个线程，都是守护线程，不会阻塞主程序执行
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
        Thread.sleep(5000L);
    }

    public static void whenCompleteTest3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });
        Thread.sleep(500);
        // future1先执行再执行whenComplete传递的action，如果future1有异常，则直接执行action
        future1.whenComplete((t, u) -> {
            log.info("whenComplete 开始执行");
            log.info("whenComplete线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        });
        // future1和whenComplete用的是同一个线程，都是守护线程，不会阻塞主程序执行
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
        Thread.sleep(5000L);
    }

    public static void whenCompleteTest4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        });
        Thread.sleep(500);
        // future1先执行再执行whenComplete传递的action，如果future1有异常，则直接执行action
        future1.whenComplete((t, u) -> {
            log.info("whenComplete 开始执行");
            log.info("whenComplete线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        }).whenComplete((t, u) -> {
            log.info("whenComplete2 开始执行");
            log.info("whenComplete2线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t2 = {}, 异常：u2 = {}", t, u);
        });
        // future1和whenComplete用的是同一个线程，都是守护线程，不会阻塞主程序执行
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
        Thread.sleep(5000L);
    }

    public static void whenCompleteAsyncTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        }, executorService);
        // future1先执行再执行whenComplete传递的action，如果future1有异常，则直接执行action
        future1.whenCompleteAsync((t, u) -> {
            log.info("whenCompleteAsync 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("whenCompleteAsync线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        });
        // future1和whenComplete用的是同一个线程，都是守护线程，不会阻塞主程序执行
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
//        Thread.sleep(5000L);
    }

    public static void thenAcceptTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        }, executorService);
        // thenAccept不会向下传递
        future1.thenAccept(e -> {
            log.info("thenAccept1 开始执行");
            System.out.println(e);
        }).thenAccept(e -> {
            log.info("thenAccept2 开始执行");
            System.out.println(e);
        }).thenAccept(e -> {
            log.info("thenAccept3 开始执行");
            System.out.println(e);
        });

    }

    public static void thenApplyTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        }, executorService);
        CompletableFuture<String> completableFuture = future1.thenApply(e -> {
            log.info("thenAccept 开始执行");
            System.out.println(e);
            return e + " =====> thenApply";
        }).thenApply(e -> {
            log.info("thenAccept2 开始执行");
            System.out.println(e);
            return e + " =====> thenApply2";
        });
        System.out.println(completableFuture.get());
    }

    public static void thenCompleteAsyncTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("future1 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("future1线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("future1 执行完毕");
            return "future1";
        }, executorService);
        future1.thenAccept(e -> {
            System.out.println(e);
        });


        future1.whenCompleteAsync((t, u) -> {
            log.info("whenCompleteAsync 是否为守护线程 ：{} ", Thread.currentThread().isDaemon());
            log.info("whenCompleteAsync线程是：{} ", Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // t是结果，u是异常
            log.info("输出结果：t = {}, 异常：u = {}", t, u);
        });
        // future1和whenComplete用的是同一个线程，都是守护线程，不会阻塞主程序执行
        System.out.println("主线程开始执行");
        // 等待5秒让future1执行完毕，因为它执行的是守护线程
//        Thread.sleep(5000L);
    }

    // <--------------------------------------  以上为API学习例子 -------------------------------------->

    public static void main(String[] args) throws Exception {
//        thenApplyTest();
//        thenAcceptTest();
//        whenCompleteAsyncTest();
//        whenCompleteTest4();
//        whenCompleteTest3();
//        whenCompleteTest2();
//        whenCompleteTest();
//        joinTest();
//        getNowTest();
//        allOfAndAnyOfTest();
//        runAsyncTest();
//        supplyAsyncTest();
        //
        demo1();
    }

    // <--------------------------------------  以下为实战例子Demo -------------------------------------->

    /**
     * 场景1：商品详情页取数据
     * 1.先获取用户信息
     * 2.根据用户并行获取专属的商品折扣信息，用户对应的购物车信息
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void demo1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CompletableFuture<Map<String, Object>> future = CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
            String userInfo = "小明";
            try {
                log.info("【User】开始获取用户信息");
                Thread.sleep(100);
                log.info("【User】用户信息获取成功 : {} ", userInfo);
                map.put("userInfo", userInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map;
        }, executorService).thenApply(result -> {
//            if (exception != null) {
//                log.info("获取用户信息异常", exception.getMessage());
//                return;
//            }
            log.info("获取到用户的信息为 ：{} ", result.get("userInfo"));
            log.info("开始获取专属的商品折扣信息，用户对应的购物车信息");
            CompletableFuture<Void> productFuture = CompletableFuture.runAsync(() -> {
                log.info("【Product】开始获取专属的商品折扣信息");
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HashMap product = new HashMap() {{
                    put("rate", "8折");
                    put("price", new BigDecimal(80));
                }};
                result.put("product", product);
                log.info("【Product】获取专属的商品折扣信息已完成");
            });
            CompletableFuture<Void> shoppingCartFuture = CompletableFuture.runAsync(() -> {
                log.info("【ShoppingCart】开始获取用户对应的购物车信息");
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result.put("shoppingCart", Arrays.asList("白T，蓝T，黄短裤"));
                log.info("【ShoppingCart】获取用户对应的购物车信息已完成");
            });
            CompletableFuture<Void> future1 = CompletableFuture.allOf(productFuture, shoppingCartFuture);
            try {
                future1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return result;
        });
        System.out.println(future.get());
    }

}
