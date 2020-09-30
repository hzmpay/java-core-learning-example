package com.hzm.freestyle.spring.designMode;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月27日
 */
public class Root implements RootInterface {

    @Override
    public void demo1() {
        System.out.println("ROOT demo1()");
    }

    protected void demo2() {
        System.out.println("ROOT demo2()");
    }

    protected void demo3() {
        System.out.println("ROOT demo3()");
    }

    protected void demo4() {
        System.out.println("ROOT demo4()");
    }
}
