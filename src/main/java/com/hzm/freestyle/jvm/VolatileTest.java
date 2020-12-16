package com.hzm.freestyle.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年06月24日
 */
public class VolatileTest {

    // 注意 isStop 变量没有用volatile修饰
// 如果用volatile修饰, 则main线程修改isStop之后, thread线程会立马停止
//    public static volatile boolean isStop = false;
    public static boolean isStop = false;

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                while (!isStop) {
                    //do nothing
//                    System.out.println("执行中");
                }
                System.out.println(System.currentTimeMillis() - l);
            }
        };
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isStop = true;
        System.out.println("Set stop is true");
    }
}
