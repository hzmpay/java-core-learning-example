package com.hzm.freestyle.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
public class DemoAopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.hzm.freestyle.spring.aop");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("方法开始执行");
        demoService.say();


    }

    public static void beanSame() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        Demo1 bean = context.getBean(Demo1.class);
        System.out.println(bean);

        DemoConfig demoConfig = context.getBean(DemoConfig.class);
        System.out.println(demoConfig.demo1());
    }
}
