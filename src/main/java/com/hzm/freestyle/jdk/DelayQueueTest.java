package com.hzm.freestyle.jdk;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月07日
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue queue = new DelayQueue();

        // 设置7秒后响应
        queue.add(new DelayedTask("i", System.currentTimeMillis() + 7000));

        while (!queue.isEmpty()) {
            Thread.sleep(2000);
            System.out.println(queue.poll());
        }

    }

    static class DelayedTask implements Delayed {

        private String name;
        private long start = System.currentTimeMillis();
        private long time;

        public DelayedTask(String name, long time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedTask o1 = (DelayedTask) o;
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }
    }

}
