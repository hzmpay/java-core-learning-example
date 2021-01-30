package com.hzm.freestyle.redis;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 模拟redis内存中读取数据测试
 * 结论：单线程支持百万级别的数据读取
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月22日
 */
public class RedisThreadTest {

    /**
     * 数据容量
     */
    public static final int COUNT = 1000 * 10000;
    /**
     * 模拟redis存储
     */
    public static final HashMap<Integer, Object> map = Maps.newHashMapWithExpectedSize(COUNT);
    /**
     * 多线程读取线程数
     */
//    public static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 多线程情况下每个线程读取数据量
     */
    public static final int DIFF_NUM = COUNT / THREAD_COUNT;
    /**
     * 多线程栅栏
     */
    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(THREAD_COUNT);

    static {
        for (int i = 0; i < COUNT; i++) {
            map.put(i, i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            map.get(i);
        }
        System.out.println("内存耗时 : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();

        int index = 0;
        for (int i = 0; i < THREAD_COUNT - 1; i++) {
            final int startIdx = index;
            index += index + DIFF_NUM;
            final int finalIndex = index;
            new Thread(() -> {
                for (int j = startIdx; j < finalIndex; j++) {
                    map.get(j);
                }
                COUNT_DOWN_LATCH.countDown();
            }).start();
        }
        // 最后一次用减的，防止余数
        final int startIdx = index;
        new Thread(() -> {
            for (int j = startIdx; j < COUNT; j++) {
                map.get(j);
            }
            COUNT_DOWN_LATCH.countDown();
        }).start();
        COUNT_DOWN_LATCH.await();
        System.out.println("多线程耗时 : " + (System.currentTimeMillis() - start));
    }
}
