package com.hzm.freestyle.javaagent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author hezeming
 * @version 1.0
 * @date 2022年12月19日
 */
public class JavaAgentTest {

    /**
     * -javaagent:/Users/hezeming/IdeaProjects/java-core-learning-example/java-agent-demo/target/java-agent-demo-0.0.1-SNAPSHOT.jar=test
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        System.out.println("hello world!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.hzm.freestyle.javaagent");
        Map<String, Handle> map = context.getBeansOfType(Handle.class);
        map.forEach((k, v) -> {
            v.say();
        });
    }
}
