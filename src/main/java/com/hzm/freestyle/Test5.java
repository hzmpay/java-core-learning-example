package com.hzm.freestyle;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月09日
 */
public class Test5 {


    public static void main(String[] args) {
//        System.out.println(0xff);
//        System.out.println(Integer.toHexString(0xff));
//        System.out.println(Integer.toString(0xff, 16));
//        System.out.println(Integer.toBinaryString(0xff));
//        System.out.println(Integer.toString(0xff, 2));

//        System.out.println(Integer.parseInt("11111111", 2));
//
//        System.out.println(Arrays.toString(hexString2Bytes("01 03 00 00 00 02 C4 0B".replaceAll(" ", ""))));
//
//        System.out.println(Integer.parseInt("0030", 16));
//        System.out.println(Integer.toBinaryString(Integer.parseInt("0030", 16)));

        new ArrayList<>().addAll(Collections.emptyList());

    }

    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }


    public static byte[] intToBytes2(int value) {
        byte[] result = new byte[2];
        result[0] = (byte) ((value >>> 8) & 0xFF);
        result[1] = (byte) (value & 0xFF);
        return result;
    }

}
