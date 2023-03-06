package com.hzm.freestyle.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月11日
 */
public class ArrayDequeTest {

    public static void main(String[] args) {

        int count = 1000000;

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }

        ArrayDeque<Integer> arrayDeque = new ArrayDeque();
        for (int i = 0; i < count; i++) {
            arrayDeque.add(i);
        }

        final long start1 = System.currentTimeMillis();



        System.out.println(System.currentTimeMillis() - start1);


        final long start2 = System.currentTimeMillis();


        System.out.println(System.currentTimeMillis() - start2);

    }
}
