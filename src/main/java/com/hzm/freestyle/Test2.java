package com.hzm.freestyle;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年10月28日
 */
public class Test2 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final ThreadPoolExecutor DEVICE_UPLOAD_DATA_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(50, 50, 1, TimeUnit.MINUTES
            , new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("deviceUploadData-pool-").build());

    public static void main(String[] args) throws Exception {
//        final Test2 test2 = new Test2();
//
//        for (int i = 0; i < 100; i++) {
////            DEVICE_UPLOAD_DATA_THREAD_POOL_EXECUTOR.execute(() -> {
//                System.out.println(test2.getCommonToken());
////            });
//        }
//        String identify = "asdad-123";
//        final int index = identify.indexOf("-");
//        String value1 = identify.substring(0, index);
//        String value2 = identify.substring(index + 1);
//        System.out.println(value1);
//        System.out.println(value2);

//        final HashMap<String, Object> map = new HashMap<>();
//
//        HashMap<String, Object> map2 = map;
//        map2.put("a", 1);
//        System.out.println(map);

//        final Test2 test2 = new Test2();
//        final long start = System.currentTimeMillis();
//        for (int i = 0; i < 4; i++) {
//            test2.getClass().getMethod("setName", String.class).invoke(test2, "哈哈哈");
//            System.out.println(test2.getName());
//        }
//        System.out.println(System.currentTimeMillis() - start);
//
//
//        Map<String, Object> map = new HashMap<>();
//        final Object a = map.putIfAbsent("a", 1);
//        System.out.println(a);

//        String a = "p1-1";
//        String a2 = new StringBuilder().append("p1").append("-").append("1").toString();
//        String[] arr = {"p1", "1"};
//
//        System.out.println(SizeEstimator.estimate(a));
//        System.out.println(SizeEstimator.estimate(a2));
//        System.out.println(SizeEstimator.estimate(arr));
        final int i = Runtime.getRuntime().availableProcessors();
        System.out.println();

    }

    /**
     * 缓存token到内存，根据最后一次获取时间刷新token
     */
    private static String TOKEN;
    /**
     * 存储最后一次获取token的时间点，根据过期时间刷新token
     */
    private static Long LAST_TIME;
    /**
     * 过期时间，单位：毫秒，存储在common配置中
     */
    private static Long TOKEN_TIME_OUT = 1000L;
    /**
     * get token lock
     */
    private static final Object GET_TOKEN_LOCK = new Object();

    public String getCommonToken() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 暂时屏蔽本地缓存
        Long oldLastTime = LAST_TIME;
        LAST_TIME = System.currentTimeMillis();
        // token不为null且token未过期则直接返回
        if (TOKEN != null && (LAST_TIME - oldLastTime < TOKEN_TIME_OUT)) {
            return TOKEN;
        }
        TOKEN = getToken();
        return TOKEN;
    }

    public String getCommonToken2() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 暂时屏蔽本地缓存
        final long currentTime = System.currentTimeMillis();
        if (TOKEN == null || currentTime - LAST_TIME >= TOKEN_TIME_OUT) {
            synchronized (GET_TOKEN_LOCK) {
                if (TOKEN == null || currentTime - LAST_TIME >= TOKEN_TIME_OUT) {
                    TOKEN = getToken();
                    LAST_TIME = System.currentTimeMillis();
                }
            }
        }
        return TOKEN;
    }

    public String getToken() {
        return LocalDateTime.now().toString();
    }
}
