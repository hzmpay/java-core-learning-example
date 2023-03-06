package com.hzm.freestyle.finalTest;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月16日
 */
public class FinalTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> FinalExample.write()).start();
        Thread.sleep(1000);
        new Thread(() -> FinalExample.reader()).start();
        System.out.println(FinalExample.obj.i);
        System.out.println(FinalExample.obj.j);
    }
}
