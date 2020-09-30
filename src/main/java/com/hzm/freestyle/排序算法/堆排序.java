package com.hzm.freestyle.排序算法;

import sun.reflect.Reflection;

import java.util.Arrays;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class 堆排序 {

    public static void main(String[] args) {
        int[] arr = {42, 31, 68, 16, 55, 23, 56, 62, 39, 10, 68, 57};
        headSort(arr);
        System.out.println(Arrays.toString(arr));
        final 堆排序 堆排序2 = new 堆排序();
        堆排序2.demO();
    }

    public void demO() {
        final Class<?> callerClass = Reflection.getCallerClass();
        System.out.println(callerClass);
    }

    /**
     * 堆排序
     */
    public static void headSort(int[] arr) {
        //构造初始堆,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = (arr.length) / 2 ; i >= 0; i--) {
            headAdjust(arr, arr.length, i);
        }

        SortUtil.treeOut(arr);

        // 调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            headAdjust(arr, i, 0);
        }
    }
    
    private static void headAdjust(int[] arr, int len, int i) {
        int k = i, temp = arr[i], index = 2 * k + 1;
        while (index < len) {
            // 最后一位
            if (index + 1 < len) {
                if (arr[index] < arr[index + 1]) {
                    index = index + 1;
                }
            }
            if (arr[index] > temp) {
                arr[k] = arr[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        arr[k] = temp;
    }

}
