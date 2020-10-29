package com.hzm.freestyle.cpu;

import com.hzm.freestyle.util.CommonUtil;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年10月24日
 */
public class L1CacheTest {

    public static void main(String[] args) throws Exception {

//        arrTest();
        final Comparator<Integer> comparator = Comparator.naturalOrder();
        final Random random = new Random();
        int count = 5000;
        final Integer[] intArr = new Integer[count];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = random.nextInt(count);
        }

        CommonUtil.timeStat(() -> {
            List<Integer> list = new ArrayList<>(count);
            IntStream.rangeClosed(1, count).forEach(e -> list.add(intArr[e - 1]));
            list.sort(comparator);
        }, 1);

        CommonUtil.timeStat(() -> {
            Set<Integer> set = new TreeSet<>(comparator);
            IntStream.rangeClosed(1, count).forEach(e -> set.add(intArr[e - 1]));
        }, 1);


    }

    public static void arrTest() {
        int max = 1024 * 1024;

        int[] arr = new int[max];

        CommonUtil.timeStat(() -> {
            for (int i = 0; i < 1000000; i += 16) {
                arr[i] = 1;
            }
        }, 100);

        int[] arr2 = new int[max];

        CommonUtil.timeStat(() -> {
            for (int i = 0; i < 1000000; i += 17) {
                arr2[i] = 1;
            }
        }, 100);
    }

    public static void arr2Test() {
        int x = 1024 * 1024;
        int y = 2;

        int[][] arrArr = new int[x][y];

        CommonUtil.timeStat(() -> {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    arrArr[i][j] = 1;
                }
            }
        }, 10);

        int[][] arrArr2 = new int[x][y];

        CommonUtil.timeStat(() -> {
            for (int j = 0; j < y; j++) {
                for (int i = 0; i < x; i++) {
                    arrArr2[i][j] = 1;
                }
            }
        }, 10);
    }

}
