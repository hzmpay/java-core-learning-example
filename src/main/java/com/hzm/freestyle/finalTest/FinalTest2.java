package com.hzm.freestyle.finalTest;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月16日
 */
public class FinalTest2 {

    private int i = 0;
    private boolean flag = false;

    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public void write() {
        i = 1;
        flag = true;
    }

    public int read() {
        int num = 2;
        if (flag) {
            num = i * i;
        }
        return num;
    }

    public static void main(String[] args) throws InterruptedException {
        IntStream.rangeClosed(1, 100000).forEach(a -> {
            FinalTest2 finalTest2 = new FinalTest2();
            new Thread(() -> {
                try {
                    COUNT_DOWN_LATCH.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finalTest2.write();
            }).start();
            new Thread(() -> {
                COUNT_DOWN_LATCH.countDown();
                System.out.println();
                if (finalTest2.read() == 0) {
                    System.out.println("出现结果等于0！");
                }
            }).start();
        });
    }
}
