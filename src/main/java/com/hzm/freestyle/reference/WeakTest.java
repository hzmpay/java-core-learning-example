package com.hzm.freestyle.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用：只存活到GC前
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月03日
 */
public class WeakTest {

    /**
     * 弱引用的理解
     * 通过设置jvm参数，在不同的条件下观察
     *
     * @param -Xms5m -Xmx5m -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        // 测试内存充足(不回收弱引用)
        testWeakReferNOGc();
        // 测试内存不充足（回收弱引用）
//        testWeakReferGc();
    }

    /**
     * 模拟内存充足的情况
     */
    public static void testWeakReferNOGc() {
        Object obj1 = new Object();
        // 建立弱引用
        WeakReference WeakRefer = new WeakReference<>(obj1);
        // 观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("WeakRefer=" + WeakRefer.get());
        // obj1创建可以回收的条件
        obj1 = null;
        // gc回收
        System.gc();
        // 再次观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("WeakRefer=" + WeakRefer.get());
    }

    /**
     * 模拟内存不足
     * 1.设置较小的堆内存
     * 2.创建大对象
     * 3.jvm参
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    public static void testWeakReferGc() {
        Object obj1 = new Object();
        // 建立弱引用
        WeakReference WeakRefer = new WeakReference<>(obj1);
        // 观察内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("WeakRefer=" + WeakRefer.get());
        // obj1创建可以回收的条件
        obj1 = null;
        try {
            byte[] bytes = new byte[6 * 1024 * 1024];
        } catch (Throwable e) {
            System.out.println("===============>error:" + e.getMessage());
        } finally {
            // 再次观察内存地址
            System.out.println("obj1=" + obj1);
            System.out.println("WeakRefer=" + WeakRefer.get());
        }
    }
}
