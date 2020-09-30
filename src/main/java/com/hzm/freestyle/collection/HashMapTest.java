package com.hzm.freestyle.collection;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月17日
 */
public class HashMapTest {


    public static void main(String[] args) {
        final HashMap<String, Object> map = new HashMap<>();
//
        IntStream.rangeClosed(1, 13).forEach(e -> {
            if (e == 13) {
                System.out.println("到了7");
            }
            map.put(e + "", e);
        });
//        final HashMapTest hashMapTest = new HashMapTest();


//        System.out.println(map.put(1 + "", 1));
//        System.out.println(map.put(11 + "", 2));
//        System.out.println(map.put(1 + "", 3));
//        System.out.println(map.put(1 + "", 4));


//
//        int[] a = new int[3];
//        int[] b;
//
//        b = a;
//        b[0] = 1;
//        a[1] = 2;

//        Arrays.stream(a).forEach(e -> System.out.println(e));
//        Arrays.stream(b).forEach(e -> System.out.println(e));

//        int len1 = 8;
//        int len2 = 10;

//        System.out.println(hash("a") % len1);
//        System.out.println(hash("a") & (len1 - 1));
//        System.out.println(hash("a") % len2);
//        System.out.println(hash("a") & (len2 - 1));

//        IntStream.rangeClosed(1, 13).forEach(e -> {
//            System.out.println(hash(e+ ""));
//        });

    }


    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
