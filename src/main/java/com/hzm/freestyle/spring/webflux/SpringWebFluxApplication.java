package com.hzm.freestyle.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月11日
 */
@SpringBootApplication
public class SpringWebFluxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebFluxApplication.class, args);
        System.out.println(context.getEnvironment());
    }
}
