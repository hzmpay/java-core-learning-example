package com.hzm.freestyle.排序算法;

import java.util.Arrays;

/**
 * 快速排序
 * https://blog.csdn.net/boy_chen93/article/details/85049274
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月01日
 */
public class 快速排序 {

    public static void main(String[] args) {
        int[] arr = {4, 1, 8, 6, 5, 3, 6, 2, 9, 10, 6, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 两个指针，分别从数组的首尾开始向中间运动，
     * （因为始终选取第一个值为temp）右指针先运动，右指针指到的数若小于枢纽元则停下来，此时，交换左右指针指向的数，
     * 左指针运动，左指针指到的数若大于或等于枢纽元则停下来，此时，交换左右指针指向的数，
     * 然后重复上述运动。直到左右指针相遇，运动结束。
     * 时间复杂度：O(nlogn)
     *
     * @param arr 需要排序的数组
     * @param i 数组需要排序的起始下标
     * @param j 数组需要排序的结束下标
     * @return void
     * @author Hezeming
     */
    public static void quickSort(int[] arr, int i, int j) {
        if (i > j) {
            return;
        }
        int left = i;
        int right = j;
        // 确定一个中间值
        int temp = arr[left];

        // 左右指针开始向中间滑动
        while (right > left) {
            // 右指针向左滑动
            while (arr[right] >= temp && right > left) {
                right--;
            }
            // 右指针滑动到比temp小的进行交换
            SortUtil.swap(arr, left, right);

            // 左指针向右滑动
            while (arr[left] <= temp && right > left) {
                left++;
            }

            // 左指针滑动到比temp大的进行交换
            SortUtil.swap(arr, left, right);
        }

        // 此时left == right == temp的值的下标
        quickSort(arr, i, left - 1);
        quickSort(arr, left + 1, j);
    }

}
