package com.hzm.freestyle.spring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import javax.annotation.Resource;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月29日
 */
@Component
@Indexed
public class A {

    @Resource
    private B b;

    @Resource
    private A a;
}
