package com.hzm.freestyle.finalTest;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月16日
 */
public class FinalExample {

    /**
     * 普通变量
     */
    int i;
    /**
     * final变量
     */
    final int j;
    /**
     * 静态变量
     */
    static FinalExample obj;

    public FinalExample() {
        // 写普通域
        i = 1;
        // 写final域
        j = 2;
    }

    public static void write() {
        obj = new FinalExample();
    }

    public static void reader() {
        // 读对象引用
        FinalExample finalExample = obj;
        // 读普通域
        int a = finalExample.i;
        // 读final域
        int b = finalExample.j;
    }
}
