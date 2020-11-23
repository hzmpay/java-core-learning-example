package com.hzm.freestyle.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月20日
 */
public class LoadingCacheTest {


    public static void main(String[] args) throws Exception {

        soft();
    }

    static class Demo {
        private Integer num;

        public Demo() {
        }

        public Demo(Integer num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "num=" + num +
                    '}';
        }
    }

    public static void soft() {
        LoadingCache<Demo, String> cache = CacheBuilder.newBuilder()
                .weakKeys()
//                .weakValues()
                .build(new CacheLoader<Demo, String>() {
                    @Override
                    public String load(Demo demo) throws Exception {
                        return "load_" + demo.num;
                    }
                });

        IntStream.rangeClosed(1, 10).forEach(e -> {
            cache.put(new Demo(e), "put_" + e);
        });
        Demo demo = new Demo(11);
        cache.put(demo, "ok");
        System.out.println(cache.asMap());
        System.gc();

        System.out.println(cache.asMap());

    }

    public static void normal() throws Exception {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(3)
                .maximumSize(4)
                .concurrencyLevel(2)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) throws Exception {
                        return "load_" + integer;
                    }
                });

        IntStream.rangeClosed(1, 10).forEach(e -> {
            cache.put(e, "put_" + e);
        });
        System.out.println(cache.asMap());

        System.out.println(cache.get(1));

        System.out.println(cache.asMap());
    }

}
