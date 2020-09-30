package com.hzm.freestyle.outOfMemoryError;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月28日
 */
public class YoungGC {

    public static final int _1MB = 1024 * 1024;

    // -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SuvivorRatio=8
    public static void main(String[] args) {

        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[Integer.MAX_VALUE * _1MB];
        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }
}
