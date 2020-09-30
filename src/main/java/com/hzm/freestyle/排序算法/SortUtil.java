package com.hzm.freestyle.排序算法;

/**
 * 排序工具类
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class SortUtil {

    /**
     * 交换元素
     *
     * @param arr
     * @param i
     * @param j
     * @return void
     * @author Hezeming
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 按照树形结构输出
     *
     * @param arr
     * @return void
     * @author Hezeming
     */
    public static void treeOut(int[] arr) {
        boolean isOver = true;
        // 每次输出个数
        int num = 1;
        // 从哪个坐标开始输出
        int index = 0;
        do {
            for (int i = 0; i < num; i++, index++) {
                if (index >= arr.length) {
                    isOver = false;
                    break;
                }
                System.out.print(arr[index] + ", ");
            }
            System.out.println();
            num = num * 2;
        } while (isOver);
    }
}
