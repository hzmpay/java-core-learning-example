package com.hzm.freestyle.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年04月30日
 */
public class CyclicBarrierTest {

    /**
     * 工人等待司机次数
     */
    public static final CountDownLatch DriverCountDownLatch = new CountDownLatch(1);

    /**
     * 工人数
     */
    public static final int num = 5;

    /**
     * 工作总次数
     */
    public static final CountDownLatch WorkerCountDownLatch = new CountDownLatch(num);

    public static void countDownLatch() throws InterruptedException {
        IntStream.rangeClosed(1, num).forEach(i -> new Thread(() -> {
            try {
                System.out.println("工人" + i + "等待司机到来");
                DriverCountDownLatch.await();
                System.out.println("工人" + i + "开始卸货");
                WorkerCountDownLatch.countDown();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start());

        Thread.sleep(2000);

        System.out.println("司机来了 ===========》");
        DriverCountDownLatch.countDown();
        System.out.println("开始等待工人卸货 ===========》");
        WorkerCountDownLatch.await();
        waitAfterHandle();
    }

    /**
     * 阻塞之后执行的业务
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void waitAfterHandle() {
        System.out.println("司机开车 ===========》");
    }

    /**
     * 声明事件
     */
    public static final CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> waitAfterHandle());

    public static void cyclicBarrier() throws InterruptedException {
        IntStream.rangeClosed(1, num).forEach(i -> new Thread(() -> {
            try {
                System.out.println("工人" + i + "等待司机到来");
                DriverCountDownLatch.await();
                Thread.sleep(2000);
                System.out.println("工人" + i + "开始卸货");
                cyclicBarrier.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start());

        Thread.sleep(2000);

        System.out.println("司机来了 ===========》");
        DriverCountDownLatch.countDown();
        System.out.println("开始等待工人卸货 ===========》");
    }

    public static final Object obj = new Object();
    public static final AtomicInteger totalNum = new AtomicInteger();

    public static void notifyWait() throws InterruptedException {
        final Thread thread = Thread.currentThread();
        System.out.println("总线程：" + thread.getName());
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, num).forEach(i -> new Thread(() -> {
            try {
                System.out.println("工人" + i + "等待司机到来");
                final Thread thread1 = Thread.currentThread();
                System.out.println("子线程：" + thread1.getName());
                // 等待500ms让线程全部执行完，再唤醒主线程的司机
                Thread.sleep(500);
                synchronized (obj) {
                    obj.notifyAll();
                    obj.wait();
                }
                Thread.sleep(10);
                synchronized (obj) {
                    System.out.println("工人" + i + "开始卸货");
                    list.add(i);
                    Thread.sleep(100);
                    if (list.size() >= num) {
                        System.out.println("工人全部卸货完成 ===========》");
                        obj.notifyAll();
                    } else {
                        obj.wait();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start());

        synchronized (obj) {
            obj.wait();
        }
        System.out.println("司机来了 ===========》");
        System.out.println("开始等待工人卸货 ===========》");
        Thread.sleep(10);
        synchronized (obj) {
            obj.notifyAll();
            obj.wait();
            System.out.println("司机开车 ===========》");
        }
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//        final long sta1 = System.currentTimeMillis();
//        countDownLatch();
//        System.out.println("countDownLatch耗时 ========================》" + (System.currentTimeMillis() - sta1));

//        notifyWait();

//        final long sta2 = System.currentTimeMillis();
//        cyclicBarrier();
//        System.out.println("cyclicBarrier耗时 ========================》" + (System.currentTimeMillis() - sta2));
//
//        Thread.sleep(5000);

//        cyclicBarrier();

//        IntStream.rangeClosed(1, num).forEach(e -> {
//            new Thread(() -> {
//                try {
//                    System.out.println("工人" + e + "卸货开始");
//                    Thread.sleep(2000);
//                    System.out.println("工人" + e + "卸货完毕");
//                    cyclicBarrier.await();
//                } catch (InterruptedException | BrokenBarrierException ex) {
//                    ex.printStackTrace();
//                }
//            }).start();
//        });
        notifyWait();
    }
}
