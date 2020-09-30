package com.hzm.freestyle.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月11日
 */
public class ThreadLocalTest {

    public static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 111);

    final static ThreadLocal<String> LOCAL = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
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
