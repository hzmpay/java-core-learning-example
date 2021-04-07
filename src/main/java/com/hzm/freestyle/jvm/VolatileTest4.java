package com.hzm.freestyle.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * volatile变量自增运算测试
 *
 * @author Hezeming
 * @version 1.0
 * @data 2021年04月06日
 */
public class VolatileTest4 {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(THREADS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                    COUNT_DOWN_LATCH.countDown();
                }
            });
            threads[i].start();
        }
        // 等待所有累加线程都结束
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
        COUNT_DOWN_LATCH.await();
        System.out.println(race);
    }
}
