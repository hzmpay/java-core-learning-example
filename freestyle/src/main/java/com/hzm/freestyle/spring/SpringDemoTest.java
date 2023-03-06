package com.hzm.freestyle.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月28日
 */
@Slf4j
public class SpringDemoTest {

    public static void main(String[] args) throws Exception {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        final A a = context.getBean(A.class);
        System.out.println(a);
        final B b = context.getBean(B.class);
        System.out.println(b);
//        final DemoBean demoBean1 = context.getBean(DemoBean.class);
//        demoBean1.say();

//        final FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("application-context.xml");
//        final DemoBean demoBean2 = context.getBean(DemoBean.class);
//        demoBean2.say();

    }
}
