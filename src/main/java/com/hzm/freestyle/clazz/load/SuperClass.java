package com.hzm.freestyle.clazz.load;

/**
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
}
