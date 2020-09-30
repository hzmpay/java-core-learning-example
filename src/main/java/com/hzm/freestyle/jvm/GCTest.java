package com.hzm.freestyle.jvm;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月03日
 */
public class GCTest {

    public static final int _1MB = 1024 * 1024;

    /**
     * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:PretenureSizeThreshold=3145728
     * -XX:MaxTenuringThreshold=1
     *
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {

        byte[] allocation1, allocation2, allocation3, allocation4;

//        allocation1 = new byte[_1MB / 2];
//        allocation2 = new byte[_1MB / 2];
        for (int i = 0; i < 1000000; i++) {
            allocation2 = new byte[1 * _1MB];

        }
        allocation2 = new byte[1 * _1MB];
        allocation3 = new byte[7 * _1MB];
//        allocation3 = new byte[8 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

}
