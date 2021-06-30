package com.hzm.freestyle.netty.hashedWheelTimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年06月22日
 */
public class HashWheelTimerTest {

    public static void main(String[] args) throws Exception {
        HashedWheelTimer timer = new HashedWheelTimer(3, TimeUnit.SECONDS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("创建成功 ===============》" + LocalDateTime.now().format(formatter));
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("开始执行 ==================》" + LocalDateTime.now().format(formatter));
            }
        }, 4, TimeUnit.SECONDS);
//        Thread.sleep(20000);
    }
}
