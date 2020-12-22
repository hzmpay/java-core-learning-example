package com.hzm.freestyle.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年06月23日
 */
public class ThreadPoolTest {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    // Packing and unpacking ctl
    private static int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private static int workerCountOf(int c) {
        return c & CAPACITY;
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    public static void main(String[] args) throws InterruptedException {

//        Executor executor = Executors.newCachedThreadPool(new ThreadFactory());

        ExecutorService executorService = Executors.newCachedThreadPool();


        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1
                , 3
                , 2
                , TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(1)
                , r -> new Thread(r)
                , (r, executor) -> {
//            r.run();
            System.out.println("拒绝策略执行");
        }
        );
        threadPoolExecutor.allowCoreThreadTimeOut(false);

        for (int i = 1; i <= 5; i++) {
            int finalI = i;

            final Thread thread = new Thread(() -> {
                try {
                    System.out.println(finalI + "开始执行");
                    Thread.sleep(1000);
                    System.out.println(finalI + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程" + i
            );
            threadPoolExecutor.execute(thread);
        }


//        threadPoolExecutor.shutdown();
//
//        System.out.println("是否中断了1" + threadPoolExecutor.isShutdown());
//        System.out.println("是否中断了2" + threadPoolExecutor.isTerminating());
//        System.out.println("是否中断了3" + threadPoolExecutor.isTerminated());
//
//        boolean isTermination = threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
//
//        System.out.println(isTermination);
    }

    public static void statis() {
        System.out.println(Integer.toBinaryString(ctl.get()));
        System.out.println(Integer.toBinaryString(COUNT_BITS));
        System.out.println(Integer.toBinaryString(CAPACITY));
        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(SHUTDOWN));
        System.out.println(Integer.toBinaryString(STOP));
        System.out.println(Integer.toBinaryString(TIDYING));
        System.out.println(Integer.toBinaryString(TERMINATED));
    }
}
