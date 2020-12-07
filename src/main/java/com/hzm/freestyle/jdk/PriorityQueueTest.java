package com.hzm.freestyle.jdk;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月07日
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();

        for (int i = 0; i < 20; i++) {
            queue.add(new Random().nextInt(100));
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
