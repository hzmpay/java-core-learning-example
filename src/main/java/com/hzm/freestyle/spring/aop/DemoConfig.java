package com.hzm.freestyle.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Configuration(proxyBeanMethods = false)
public class DemoConfig {

    @Bean
    public Demo1 demo1() {
        return new Demo1();
    }

}
