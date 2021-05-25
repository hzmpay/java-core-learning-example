package com.hzm.freestyle.jdk;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月24日
 */
public class FlatMapTest {

    /**
     * 将["a,b,c","d,e,f"]转为["a","b","c","d","e","f"]
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void test1() {
        List<String> data = Arrays.asList("a,b,c", "c,c,d,e,f");
        // 第一种写法
        List<String> list1 = data.stream()
                .flatMap(e -> Arrays.stream(e.split(",")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list1);
        // 第二种写法
        List<String> list2 = data.stream()
                .map(e -> e.split(",")).flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list2);
    }

    /**
     * 将["a,b,c","d,e,f"]转为["a","b","c","d","e","f"]
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void test2() {
        Map<String, Object> map1 = new HashMap(){{
            put("a", "a");
            put("b", "b");
        }};
        Map<String, Object> map2 = new HashMap(){{
            put("c", "c");
            put("d", "d");
        }};
        Map<String, Object> map3 = new HashMap(){{
            put("c", "c");
            put("d", "d");
        }};


        List<String> data = Arrays.asList("a,b,c", "d,e,f");
        // 第一种写法
        List<String> list1 = data.stream()
                .flatMap(e -> Arrays.stream(e.split(",")))
                .collect(Collectors.toList());
        System.out.println(list1);
        // 第二种写法
        List<String> list2 = data.stream()
                .map(e -> e.split(",")).flatMap(Arrays::stream)
                .collect(Collectors.toList());
        System.out.println(list2);
    }

    public static List<String> serviceB(String a) {
        if (a.equals("3")) {
            return Arrays.asList("5","6","7");
        }
        if (a.equals("5")) {
            return Arrays.asList("7","8","9");
        }
        return null;
    }

    /**
     * 根据list的每个元素调用服务B得到结果，根据返回结果做判断，存在则添加到result（常规版本）
     *
     * @param
     * @return java.util.List<java.lang.String>
     * @author Hezeming
     */
    public static List<String> listNotNullAdd1() {
        List<String> result = new ArrayList<>();
        List<String> list = Arrays.asList("1", "2", "3");
        // 根据list的每个元素调用服务B得到结果，根据返回结果做判断，存在则添加到result
        for (String s : list) {
            List<String> list1 = serviceB(s);
            if (list1 != null) {
                result.addAll(list1);
            }
        }
        return result;
    }

    /**
     * 根据list的每个元素调用服务B得到结果，根据返回结果做判断，存在则添加到result（lambda手动判断空版本）
     *
     * @param
     * @return java.util.List<java.lang.String>
     * @author Hezeming
     */
    public static List<String> listNotNullAdd2() {
        List<String> list = Arrays.asList("1", "2", "3");
        // 根据list的每个元素调用服务B得到结果，根据返回结果做判断，存在则添加到result
        List<String> result = list.stream().flatMap(e -> {
            List<String> list1 = serviceB(e);
            if (list1 == null) {
                return null;
            }
            return list1.stream();
        }).collect(Collectors.toList());
        return result;
    }

    public static void main(String[] args) {
        System.out.println(listNotNullAdd2());
    }
}
