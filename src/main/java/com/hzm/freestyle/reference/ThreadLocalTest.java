package com.hzm.freestyle.reference;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月04日
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("哈哈哈");

        System.out.println("ThreadValue = " + threadLocal.get());

        System.gc();

        System.out.println("ThreadValue = " + threadLocal.get());

    }
}
