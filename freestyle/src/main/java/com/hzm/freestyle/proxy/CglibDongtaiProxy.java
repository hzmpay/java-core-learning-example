package com.hzm.freestyle.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月05日
 */
public class CglibDongtaiProxy {


    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    }


    @EnableAspectJAutoProxy
    class DemoMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before =============>");
            final Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("after =============>");
            return result;
        }
    }

    class Demo {

        public void say() {
            System.out.println("hello .......");
        }
    }


}
