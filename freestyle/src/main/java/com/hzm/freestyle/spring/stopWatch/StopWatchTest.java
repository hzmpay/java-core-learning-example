package com.hzm.freestyle.spring.stopWatch;

import org.springframework.util.StopWatch;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月08日
 */
public class StopWatchTest {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(2000L);
        stopWatch.stop();

        System.out.println(stopWatch);
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
