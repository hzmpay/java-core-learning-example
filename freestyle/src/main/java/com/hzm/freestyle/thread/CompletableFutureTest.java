package com.hzm.freestyle.thread;

/**
 * CompletableFuture测试
 *
 * @author Hezeming
 * @version 1.0
 * @date 2019年11月12日
 */
public class CompletableFutureTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 利用Thread.currentThread.getName()获取当前线程名字。
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程："+Thread.currentThread().getName() + ":" + i);
                }
                System.out.println("子线程执行完毕。。。。");
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程："+Thread.currentThread().getName() + ":" + i);
        }
        System.out.println("主线程执行完毕。。。。");

    }

    public static void demo() {
        final Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("结束了==========");
            }
            System.out.println("执行了。。。");
        });

//        Thread.currentThread().setDaemon(true);

        thread.setDaemon(true);

        thread.start();
        System.out.println("主线程结束了==========");
    }

}
