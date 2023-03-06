package com.hzm.freestyle.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月16日
 */
public class JoinTest {

    public static Semaphore semaphore1 = new Semaphore(1);
    public static Semaphore semaphore2 = new Semaphore(0);
    public static Semaphore semaphore3 = new Semaphore(0);

    public static final ReentrantLock LOCK = new ReentrantLock();

    public static final Condition condition1 = LOCK.newCondition();
    public static final Condition condition2 = LOCK.newCondition();
    public static final Condition condition3 = LOCK.newCondition();


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new myThread1().start();
            new myThread2().start();
            new myThread3().start();
        }

    }

    static class myThread1 extends Thread {

        @SneakyThrows
        @Override
        public void run() {
//            condition1.await();
//            System.out.println(getClass());
//            condition2.signal();
            semaphore1.acquire();
            System.out.println(getClass());
            semaphore2.release();
        }
    }


    static class myThread2 extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            semaphore2.acquire();
            System.out.println(getClass());
            semaphore3.release();
        }
    }


    static class myThread3 extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            semaphore3.acquire();
            System.out.println(getClass());
            semaphore1.release();
        }
    }
}
