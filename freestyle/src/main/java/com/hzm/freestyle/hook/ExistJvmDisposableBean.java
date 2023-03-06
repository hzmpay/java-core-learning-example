package com.hzm.freestyle.hook;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月02日
 */
@Component
public class ExistJvmDisposableBean implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("ExistJvmDisposableBean execute ......");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy method execute ......");
    }
}
