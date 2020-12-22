package com.hzm.freestyle.alibaba;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月16日
 */
public class 生产者消费者 {

    public static final Queue<Integer> QUEUE = new LinkedList<>();

    public static final int size = 10;

    public static final Object OBJECT = new Object();



    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                new Producer(i + 1).start();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                new Consumer().start();
            }
        }).start();
    }

    static class Consumer extends Thread{

        @SneakyThrows
        @Override
        public void run() {
            synchronized (OBJECT) {
                while (QUEUE.size() == 0) {
                    OBJECT.wait();
                }
                System.out.println("Consumer get {" + QUEUE.poll() + "} 。。。。。。");
                OBJECT.notify();
            }
        }
    }

    static class Producer extends Thread{

        private int num;

        public Producer(int num) {
            this.num = num;
        }

        @SneakyThrows
        @Override
        public void run() {
            synchronized (OBJECT) {
                while (QUEUE.size() == size) {
                    OBJECT.wait();
                }
                System.out.println("Producer add {" + num + "} 。。。。。。");
                QUEUE.add(num);
                Thread.sleep(1000);
                OBJECT.notify();
            }
        }
    }
}
