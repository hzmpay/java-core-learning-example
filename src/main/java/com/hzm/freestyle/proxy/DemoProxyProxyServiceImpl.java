package com.hzm.freestyle.proxy;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class DemoProxyProxyServiceImpl implements DemoProxyService, DemoProxyService2 {
    
    @Override
    public void say() {
        System.out.println("打印say()方法。。。。。。。");
    }

    @Override
    public void say2() {
        System.out.println("打印say2()方法。。。。。。。");
    }
}
