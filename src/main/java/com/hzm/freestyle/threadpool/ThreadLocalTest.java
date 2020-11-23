package com.hzm.freestyle.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月11日
 */
public class ThreadLocalTest {

    public static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 111);

    static ThreadLocal<String> LOCAL = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        Map<ThreadLocal, Object> map = new HashMap<>();
        map.put(LOCAL, "123");
        System.out.println(map);

        LOCAL = null;

        System.out.println(map);
    }

    public static void test2() {
        LOCAL.set("1");
        Thread thread = Thread.currentThread();
        LOCAL = null;
        System.gc();
//        System.out.println(LOCAL.get());
    }

    public static void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 线程1
        executorService.execute(() -> {
            // 存值
            LOCAL.set(Thread.currentThread().getName());
            try {
                // 停顿一秒，以便先在gc，再get
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取值
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });
        // 线程2
        executorService.execute(() -> {
            LOCAL.set(Thread.currentThread().getName());
            try {
                // 停顿一秒，以便先在gc，再get
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" +LOCAL.get());
        });
        System.gc();
        executorService.shutdown();
    }

}
