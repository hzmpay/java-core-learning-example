package com.hzm.freestyle.alibaba;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月11日
 */
public class Test {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(Integer.SIZE));
        System.out.println(Integer.toBinaryString(CAPACITY));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

    }


}
