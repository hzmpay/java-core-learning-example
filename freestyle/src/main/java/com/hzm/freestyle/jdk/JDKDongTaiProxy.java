package com.hzm.freestyle.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年06月29日
 */
public class JDKDongTaiProxy implements InvocationHandler {

    private Object target;

    public JDKDongTaiProxy(Object target) {
        super();
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法开始了");
        final Object invoke = method.invoke(proxy, args);

        return invoke;
    }
}
