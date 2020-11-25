package com.hzm.freestyle.jdk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月25日
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        System.out.println("AaAa".hashCode());
        System.out.println("BBBB".hashCode());
//        System.out.println("removeNodeStatusScreen".hashCode());
//        System.out.println("removeNodeStatusPresenter".hashCode());

        //正常用法
        Map<String, String> map = new ConcurrentHashMap<>();
        String value1 = map.computeIfAbsent("AaAa", n -> "123");
        System.out.println(value1);
        //bug重现
        String s = map.computeIfAbsent("AaAa", n -> {
            return map.computeIfAbsent("BBBB", m -> "1233");
        });

        System.out.println(s);

    }
}
