package com.hzm.freestyle.排序算法;

import java.util.Arrays;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class 选择排序 {

    public static void main(String[] args) {
        int[] arr = {4, 1, 8, 6, 5, 3, 6, 2, 9, 10, 6, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 和冒泡排序差不多，也是从第一位开始和后面的数逐一比较，
     * 区别在于选择排序是记录下最小数的下标，最后和第一位进行交换
     * 时间复杂度：O(n2)
     *
     * @param arr
     * @return void
     * @author Hezeming
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                SortUtil.swap(arr, i, min);
            }
        }
    }
}
