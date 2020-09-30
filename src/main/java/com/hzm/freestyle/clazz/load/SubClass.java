package com.hzm.freestyle.clazz.load;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */
public class SubClass extends SuperClass {

    public static int value = 456;

    static {
        System.out.println("SubClass init!");
    }
}
