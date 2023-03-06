package com.hzm.freestyle.spring;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月29日
 */
@Component
public class B {

    @Resource
    private A a;
}
