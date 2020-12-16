package com.hzm.freestyle.jvm;

/**
 * volatile变量自增运算测试
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年06月24日
 */
public class VolatileTest3 {

    public static int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 30;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (race < 1000) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        // 等待所有累加线程都结束
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
        Thread.sleep(3000);
        System.out.println(race);
    }
}
