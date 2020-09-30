package com.hzm.freestyle.concurrent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2019年11月03日
 */
public class CountDownLatchTest {

    private static final RestTemplate restTemplate = new RestTemplate();


    private static final CountDownLatch countDownLatch = new CountDownLatch(10);

    private static final CountDownLatch countDownLatchOne = new CountDownLatch(1);

    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        final long stat = System.currentTimeMillis();



        IntStream.rangeClosed(1, 10).forEach(e -> {

            new Thread(() -> {
                sleep();
//                System.out.println(e);
                list1.add(e);
                countDownLatch.countDown();
            }).start();
        });

        countDownLatch.await();

//        Thread.sleep(5000);
        System.out.println("demo3总耗时：" + (System.currentTimeMillis() - stat));
        System.out.println(list1);

    }

    private static void countDownLatchTest() throws InterruptedException {
        new Thread(() -> demo1()).start();
        new Thread(() -> demo2()).start();
        new Thread(() -> {
            try {
                demo3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(500000);

    }

    public static final int maxCount = 10;

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void demo1() {

        final long stat = System.currentTimeMillis();

        IntStream.rangeClosed(1, maxCount).forEach(e -> {
            sleep();
//            System.out.println("demo1");
        });

        System.out.println("demo1总耗时：" + (System.currentTimeMillis() - stat));
    }

    private static void demo2() {

        final long stat = System.currentTimeMillis();

        for (int i = 1; i <= maxCount; i++) {
            sleep();
        }

        System.out.println("demo2总耗时：" + (System.currentTimeMillis() - stat));
    }

    private static void demo3() throws InterruptedException {

        final long stat = System.currentTimeMillis();

        IntStream.rangeClosed(1, 10).forEach(e -> {

            new Thread(() -> {
                sleep();
                countDownLatch.countDown();
            }).start();
        });

        countDownLatch.await();

        System.out.println("demo3总耗时：" + (System.currentTimeMillis() - stat));
    }

    private static void test() throws Exception {
        IntStream.rangeClosed(1, 10).forEach(num -> new MyThread(num).start());

        System.out.println(countDownLatch.getCount());

        new Thread(CountDownLatchTest::run).start();

        System.out.println("开始等待");
        countDownLatch.await();
        System.out.println("所有线程都已执行完毕");

        Thread.sleep(500000);
    }

    private static void run() {
        while (true) {
            try {
                Thread.sleep(500);
                System.out.println("还剩个数：" + countDownLatch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyThread extends Thread {

        private Integer num;

        public MyThread(Integer num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(String.format("%d ---- %s", num, LocalDateTime.now()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void demo() {
        String[] items = {
                "https://item.jd.com/6784496.html", "https://item.jd.com/5089253.html", "https://item.jd.com/5089273.html", "https://item.jd.com/100000177760.html", "https://item.jd.com/100000287113.html", "https://item.jd.com/1861101.html", "https://item.jd.com/3133847.html", "https://item.jd.com/100000177748.html", "https://item.jd.com/11794447957.html", "https://item.jd.com/31545088844.html", "https://item.jd.com/16580586466.html", "https://item.jd.com/27424489997.html", "https://item.jd.com/11464031106.html", "https://item.jd.com/34803424704.html", "https://item.jd.com/10889864876.html", "https://item.jd.com/16580070299.html", "https://item.jd.com/31544565956.html", "https://item.jd.com/32943442167.html", "https://item.jd.com/16580068432.html", "https://item.jd.com/32242288360.html", "https://item.jd.com/11357751613.html", "https://item.jd.com/32943757456.html", "https://item.jd.com/32943574369.html", "https://item.jd.com/32858702835.html", "https://item.jd.com/25836502018.html"
        };
        final int size = items.length;
        List<String> urls = Arrays.stream(items).map(item -> String.format("https://p.3.cn/prices/mgets?skuIds=J_%s&type=1", item.substring(item.lastIndexOf('/') + 1, item.length() - 5)))
                .collect(Collectors.toList());

        // 先初始化好线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(size, size, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(size), new ThreadPoolExecutor.AbortPolicy());
        // 提前启动好所有的线程
        threadPool.prestartAllCoreThreads();
        // 华丽的分割线
        final String separatorLine = String.join("", Collections.nCopies(10, "="));

        // 咱们这里请求三次，做好预热，以最后一次结果为准
        IntStream.rangeClosed(1, 3).forEach((x) -> {
            try {
                fetch(urls, threadPool);
                System.out.println(separatorLine);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadPool.shutdownNow();
    }

    private static void fetch(List<String> urls, ThreadPoolExecutor threadPool) throws InterruptedException {
        // 使用CountDownLatch并发请求
        CountDownLatch latch = new CountDownLatch(urls.size());
        // 发送请求：
        long start = System.nanoTime();
        List<Future<List<Value>>> futureList = urls.stream().map(url -> threadPool.submit(new FetchTask(url, latch))).collect(Collectors.toList());
        latch.await();
        double sum = futureList.stream().mapToDouble(value -> {
            try {
                return value.get().get(0).getP();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).sum();
        System.out.println("time is " + (System.nanoTime() - start) / (1000.00D * 1000.00D) + "ms");
        System.out.println("sum is " + sum);
    }


    private static class FetchTask implements Callable<List<Value>> {
        private String url;
        private CountDownLatch latch;

        FetchTask(String url, CountDownLatch latch) {
            this.url = url;
            this.latch = latch;
        }

        @Override
        public List<Value> call() {
            try {
                return restTemplate.exchange(url, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<Value>>() {
                        }
                ).getBody();
            } finally {
                latch.countDown();
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Value {
        private String id;
        private Double p;
    }
}
