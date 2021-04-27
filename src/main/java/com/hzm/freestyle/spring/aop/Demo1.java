package com.hzm.freestyle.spring.aop;

import java.util.Random;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
public class Demo1 {

    private String name;

    private int age;

    public Demo1() {
        age = new Random().nextInt(100);
    }
}
