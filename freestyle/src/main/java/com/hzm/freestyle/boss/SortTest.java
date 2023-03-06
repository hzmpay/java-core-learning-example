package com.hzm.freestyle.boss;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月14日
 */
public class SortTest {

    public static void main(String[] args) {
//        Random random = new Random();
//        int[] array = new int[10000000];
//        for (int i = 0; i < 10000000; i++) {
//            array[i] = random.nextInt(10000000);
//        }
        int[] array = {123,1243,234325,1,5,3};


        quickSort(array, 0, array.length - 1);
        System.out.println(array[0]);
        System.out.println(array[1]);
    }

    public static void print(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 快排
     *
     * @param arr 需要排序的数组
     * @param left 左指针
     * @param right 右指针
     * @return void
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        // 确定一个中间值
        int temp = arr[l];

        // 左右指针开始向中间滑动，直到碰撞
        while (l < r) {
            // 右指针向左滑动
            while (arr[r] >= temp && r > l) {
                r--;
            }

            // 右指针滑动到比temp小的进行交换
            swap(arr, l, r);

            // 左指针向右滑动
            while (arr[l] <= temp && r > l) {
                l++;
            }

            // 左指针滑动到比temp大的进行交换
            swap(arr, l, r);
        }

        // 此时left == right == temp的值的下标
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
