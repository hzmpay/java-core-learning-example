package com.hzm.freestyle.reference;

/**
 * 强引用
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月03日
 */
public class StrongTest {

    /**
     * 强引用的理解
     *
     * @param args
     */
    public static void main(String[] args) {
        Object obj1 = new Object();
        // 建立强引用
        Object obj2 = obj1;
        // 观察obj1 和 obj2 的各种内存地址
        System.out.println("obj1=" + obj1);
        System.out.println("obj2=" + obj2);
        // obj1创建可以回收的条件
        obj1 = null;
        // gc回收
        System.gc();
        // 观察各对象情况
        System.out.println("obj1=" + obj1);
        System.out.println("obj2=" + obj2);
    }
}
