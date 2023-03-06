package com.hzm.freestyle.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月02日
 */
public class CpuCountTest {

    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();

    public static final int COUNT = 1 * 1000 * 10000;

    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(COUNT);

    public static void main(String[] args) throws Exception {

        calc(CPU_NUM * 2);
        calc(CPU_NUM * 2 + 1);
        calc(CPU_NUM * 2 + 2);
        calc(CPU_NUM);
        calc(CPU_NUM + 1);
        calc(CPU_NUM + 2);

        System.out.println("临界 ===========》");

        calc2(CPU_NUM * 2);
        calc2(CPU_NUM * 2 + 1);
        calc2(CPU_NUM * 2 + 2);
        calc2(CPU_NUM);
        calc2(CPU_NUM + 1);
        calc2(CPU_NUM + 2);

    }

    public static void calc(int corePoolSize) throws InterruptedException {
        calc(corePoolSize, corePoolSize);
    }

    public static void calc2(int corePoolSize) throws InterruptedException {
        calc(corePoolSize, corePoolSize * 2);
    }

    public static void calc(int corePoolSize, int maximumPoolSize) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.prestartAllCoreThreads();
        final long start = System.currentTimeMillis();
        AtomicInteger i = new AtomicInteger();
        for (int j = 0; j < COUNT; j++) {
            threadPoolExecutor.execute(() -> {
                i.incrementAndGet();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("耗时 ====》 " + (System.currentTimeMillis() - start));
    }

}
