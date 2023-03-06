package com.hzm.freestyle.jvm;

/**
 * https://blog.csdn.net/kavito/article/details/82292035
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月03日
 */
public class GCTest {

    public static final int _1MB = 1024 * 1024;

    /**
     * java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -XX:PretenureSizeThreshold=3145728
     * -XX:MaxTenuringThreshold=15
     *
     * 备注：idea本身会占用eden区44%的内存
     *
     * 结论：
     * 1.使用 -XX:+UseSerialGC（Serial + Serial Old）：放入对象时，新生代内存不足的话，把新生代的对象转移到老生代，然后把新对象放入腾空的新生代。
     * 2.使用 -XX:+UseParallelGC（Parallel Scavenge + Serial Old）：放入对象时，在GC前还会进行一次判断，如果要分配的内存>=Eden区大小的一半，那么会直接把要分配的内存放入老年代中。否则才会进入担保机制。
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[3 * _1MB];
//        allocation3 = new byte[4 * _1MB];
//        allocation2 = new byte[5 * _1MB];
//        allocation4 = new byte[1 * _1MB];
//        allocation2 = null;
        System.gc();
//        allocation2 = null;
//        System.gc();
    }

//        allocation1 = new byte[_1MB / 2];
//        allocation2 = new byte[_1MB / 2];
//        for (int i = 0; i < 1000000; i++) {
//            allocation2 = new byte[1 * _1MB];
//        }
//        allocation4 = new byte[3 * _1MB];
//        allocation3 = new byte[7 * _1MB];
//        allocation3 = new byte[8 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
//    }

}
