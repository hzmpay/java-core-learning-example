package com.hzm.freestyle.boss;

import lombok.SneakyThrows;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月14日
 */
public class ThreadTest {

    private static final Object LOCK = new Object();
    private static boolean isA = false;
    private static boolean isB = false;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new ThreadB().start();
            new ThreadC().start();
            new ThreadA().start();
        }
    }

    static class ThreadA extends Thread {

        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println("A execute ....");
                isA = true;
                LOCK.notifyAll();
            }
        }
    }

    static class ThreadB extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            synchronized (LOCK) {
                while (!isA) {
                    LOCK.wait();
                }
                System.out.println("B execute ....");
                isB = true;
                LOCK.notifyAll();
                // 恢复A标识
                isA = false;
            }
        }
    }

    static class ThreadC extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            synchronized (LOCK) {
                while (!isB) {
                    LOCK.wait();
                }
                System.out.println("C execute ....");
                LOCK.notifyAll();
                // 恢复B标识
                isB = false;
            }
        }
    }
}
