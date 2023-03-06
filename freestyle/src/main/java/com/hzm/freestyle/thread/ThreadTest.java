package com.hzm.freestyle.thread;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月26日
 */
public class ThreadTest {

    public static void main(String[] args) {

        Thread thread = new Thread();

        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());
        thread.start();

        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());

        System.out.println("========================");

        thread.interrupt();
//        Thread.currentThread().interrupt();

        System.out.println(thread.isAlive());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());
        System.out.println(thread.isAlive());

        System.out.println("========================");

        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());





    }

}
