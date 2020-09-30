package com.hzm.freestyle.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月03日
 */
public class SoftTest {

    /**
     * 软引用的理解
     * 通过设置jvm参数，在不同的条件下观察
     *
     * @param -Xms5m -Xmx5m -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        // 测试内存充足(不回收软引用)
//        testSoftReferNOGc();
        // 测试内存不充足（回收软引用）
        testSoftReferGc();
    }

    /**
     * 模拟内存充足的情况
     */
    public static void testSoftReferNOGc() {
        Object obj1 = new Object();
        // 建立软引用
        SoftReference softRefer = new SoftReference<>(obj1);
        // 观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("softRefer=" + softRefer.get());
        // obj1创建可以回收的条件
        obj1 = null;
        // gc回收
        System.gc();
        // 再次观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("softRefer=" + softRefer.get());
    }

    /**
     * 模拟内存不足
     * 1.设置较小的堆内存
     * 2.创建大对象
     * 3.jvm参
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void testSoftReferGc() {
        Object obj1 = new Object();
        // 建立软引用
        SoftReference softRefer = new SoftReference<>(obj1);
        // 观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("softRefer=" + softRefer.get());
        // obj1创建可以回收的条件
        obj1 = null;
        try {
            byte[] bytes = new byte[8 * 1024 * 1024];
        } catch (Throwable e) {
            System.out.println("===============>error:" + e.getMessage());
        } finally {
            // 再次观察内存地址
            System.out.println("obj1=" + obj1);
            System.out.println("softRefer=" + softRefer.get());
        }
    }
}
