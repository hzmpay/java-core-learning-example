package com.hzm.freestyle.javaagent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author hezeming
 * @version 1.0
 * @date 2022年12月19日
 */
public abstract class AbstractHandle implements Handle {

    @Override
    public void say() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getClass().getSimpleName());
    }
}
