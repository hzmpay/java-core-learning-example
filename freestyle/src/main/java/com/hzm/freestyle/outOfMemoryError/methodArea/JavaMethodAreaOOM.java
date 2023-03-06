package com.hzm.freestyle.outOfMemoryError.methodArea;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区OOM
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月11日
 */
public class JavaMethodAreaOOM {

    /**
     * * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }
    static class OOMObject {
    }
}
