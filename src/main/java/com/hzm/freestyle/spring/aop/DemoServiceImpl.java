package com.hzm.freestyle.spring.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void say() {
        System.out.println("say ==========>");
        ((DemoService) AopContext.currentProxy()).say2();
    }

    @Override
    public void say2() {
        System.out.println("say2 ==========>");
    }
}
