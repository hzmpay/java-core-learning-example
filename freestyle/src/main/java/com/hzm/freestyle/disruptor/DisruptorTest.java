package com.hzm.freestyle.disruptor;

public class DisruptorTest {

    public static void main(String[] args) {
        Long[] arr = new Long[64 * 1024 * 1024];
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 3L;
        }
        System.out.println(System.nanoTime() - start);
        long start2 = System.nanoTime();
        for (int i = 0; i < arr.length; i += 8) {
            arr[i] = 3L;
        }
        System.out.println(System.nanoTime() - start2);
    }
}
