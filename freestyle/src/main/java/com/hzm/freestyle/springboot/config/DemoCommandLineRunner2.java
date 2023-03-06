package com.hzm.freestyle.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Component
public class DemoCommandLineRunner2 implements CommandLineRunner {

    @Value("${demo.properties.personName:null}")
    private String personName;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(getClass() + "输出 ：" + personName);
    }
}
