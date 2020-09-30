package com.hzm.freestyle.clazz;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */
public class InitClassTest {

    static int a = 1;

    static {
        a = 2;
        System.out.println(a);
    }

    public static void main(String[] args) {

    }

}
