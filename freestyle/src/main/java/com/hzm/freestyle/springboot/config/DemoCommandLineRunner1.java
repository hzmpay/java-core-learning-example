package com.hzm.freestyle.springboot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Component
public class DemoCommandLineRunner1 implements CommandLineRunner {

    @Resource
    private DemoProperties demoProperties;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(getClass() + "输出 ：" + demoProperties.getPersonName());
    }
}
