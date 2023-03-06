package com.hzm.freestyle.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月21日
 */
public class ReadWriteLockTest {


    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    public static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                readLock.lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("进入" + finalI);
            }).start();
        }

    }

    public static void demo() {
        System.out.println("---------------main方法执行----------------------");
        // 获取线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 无需获取同步的monitor和synchronizer信息
        // 仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        System.out.println("-------------开始打印线程信息------------");
        for (ThreadInfo threadInfo : threadInfos) {
            // 获取线程id和线程名
            System.out.println("[" + threadInfo.getThreadId() + "]" + "------->" + threadInfo.getThreadName());
        }
        System.out.println("main方法执行完毕");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    public static void lockTest() {

//        for (int i = 0; i < 50; i++) {
//            int finalI = i;
            int finalI = 1;
            new Thread(() -> {
                lock.writeLock().lock();
                lock.readLock().lock();
                System.out.println("开始进行写操作===>" + finalI);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println("写操作完成===>" + finalI);
                lock.readLock().unlock();
            }).run();
//        }

    }

    public static void synTest() {

        new Thread(() -> {
//            synchronized (ReadWriteLockTest.class) {
//                System.out.println("开始进行写操作");
//                try {
//                    Thread.sleep(1000000);
//                } catch (InterruptedException e) {
//                }
//                System.out.println("写操作完成");
//            }
            try {
                System.out.println("开始进行写操作");
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                }
        }).run();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("执行");
            }).run();
        }
    }
}
