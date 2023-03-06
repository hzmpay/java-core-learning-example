package com.hzm.freestyle.排序算法;

import java.util.Arrays;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class 冒泡排序 {

    public static void main(String[] args) {
        int[] arr = {4, 1, 8, 6, 5, 3, 6, 2, 9, 10, 6, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 对于一组需要排序的数字，依次将个位置上的数字与逐一与其之后的数字进行比较，如果他们的顺序错误就把他们交换过来。
     * 这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的顶端，故名。
     * 时间复杂度：O(n2)
     *
     * @param arr
     * @return void
     * @author Hezeming
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    SortUtil.swap(arr, i, j);
                }
            }
        }
    }

}
